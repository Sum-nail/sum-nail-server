package backend.sumnail.medium;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount.Profile;
import backend.sumnail.domain.auth.controller.dto.request.AuthLoginRequest;
import backend.sumnail.domain.auth.controller.dto.request.AuthRefreshRequest;
import backend.sumnail.domain.auth.service.helper.GoogleClient;
import backend.sumnail.domain.auth.service.helper.KakaoClient;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.global.config.jwt.JwtTokenProviderImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/auth-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class AuthControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoogleClient googleClient;

    @MockBean
    private KakaoClient kakaoClient;

    @MockBean
    private JwtTokenProviderImpl jwtTokenProvider;

    @Test
    @DisplayName("카카오 로그인을 할 수 있다.")
    void kakaoSignInTest() throws Exception{
        //given
        AuthLoginRequest request = AuthLoginRequest.builder()
                .provider("kakao")
                .idToken("idToken")
                .build();
        BDDMockito.given(kakaoClient.getUserInfo("Bearer idToken"))
                .willReturn(AuthKakaoLoginDto.builder()
                        .kakaoAccount(KakaoAccount.builder()
                                .profile(Profile.builder()
                                        .nickname("썸네일")
                                        .thumbnailImageUrl("https://guardian.co.uk/one")
                                        .build())
                                .email("sed@yahoo.edu")
                                .build())
                        .build());
        BDDMockito.given(jwtTokenProvider.generateAccessToken(any(User.class)))
                .willReturn("accessToken");
        BDDMockito.given(jwtTokenProvider.generateRefreshToken(any(User.class)))
                .willReturn("refreshToken");

        //when
        //then
        mockMvc.perform(post("/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
    }

    @Test
    @DisplayName("구글 로그인을 할 수 있다.")
    void googleSignInTest() throws Exception{
        //given
        AuthLoginRequest request = AuthLoginRequest.builder()
                .provider("google")
                .idToken("idToken")
                .build();
        BDDMockito.given(googleClient.getUserInfo("idToken"))
                .willReturn(AuthGoogleLoginDto.builder()
                        .email("sed@yahoo.edu")
                        .name("썸네일")
                        .picture("https://guardian.co.uk/one")
                        .build());
        BDDMockito.given(jwtTokenProvider.generateAccessToken(any(User.class)))
                .willReturn("accessToken");
        BDDMockito.given(jwtTokenProvider.generateRefreshToken(any(User.class)))
                .willReturn("refreshToken");

        //when
        //then
        mockMvc.perform(post("/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));

    }

    @Test
    @DisplayName("provider가 카카오나 구글이 아닐 때 요청할 경우 400 응답을 받는다.")
    void SignInBadRequestTest() throws Exception {
        //given
        AuthLoginRequest request = AuthLoginRequest.builder()
                .provider("naver")
                .idToken("idToken")
                .build();
        //when
        //then
        mockMvc.perform(post("/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.error").value("유효하지 않은 providerName 입니다."));
    }

    @Test
    @DisplayName("토큰을 리프레시 할 수 있다.")
    void refreshTest() throws Exception{
        //given
        AuthRefreshRequest request = AuthRefreshRequest.builder()
                .refreshToken("refreshToken")
                .build();
        BDDMockito.given(jwtTokenProvider.validateRefreshToken("refreshToken"))
                .willReturn(true);
        BDDMockito.given(jwtTokenProvider.getUserIdFromRefreshToken("refreshToken"))
                .willReturn(1L);
        BDDMockito.given(jwtTokenProvider.generateAccessToken(any(User.class)))
                .willReturn("accessToken");
        BDDMockito.given(jwtTokenProvider.generateRefreshToken(any(User.class)))
                .willReturn("refreshToken");

        //when
        //then
        mockMvc.perform(post("/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
    }

    @Test
    @DisplayName("토큰이 일치하지 않을 때 요청할 경우 401 응답을 받는다.")
    void refreshUnauthorizedTest() throws Exception{
        //given
        AuthRefreshRequest request = AuthRefreshRequest.builder()
                .refreshToken("refreshToken2")
                .build();
        BDDMockito.given(jwtTokenProvider.validateRefreshToken("refreshToken"))
                .willReturn(true);
        BDDMockito.given(jwtTokenProvider.getUserIdFromRefreshToken("refreshToken"))
                .willReturn(1L);

        //when
        //then
        mockMvc.perform(post("/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value("UNAUTHORIZED"))
                .andExpect(jsonPath("$.error").value("토큰이 유효하지 않습니다."));
    }


}