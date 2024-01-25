package backend.sumnail.medium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount.Profile;
import backend.sumnail.domain.auth.controller.dto.response.AuthTokenResponse;
import backend.sumnail.domain.auth.service.AuthService;
import backend.sumnail.domain.auth.service.helper.GoogleClient;
import backend.sumnail.domain.auth.service.helper.KakaoClient;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.global.config.jwt.JwtTokenProviderImpl;
import backend.sumnail.global.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/auth-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @MockBean
    private GoogleClient googleClient;

    @MockBean
    private KakaoClient kakaoClient;

    @MockBean
    private JwtTokenProviderImpl jwtTokenProvider;


    @Test
    @DisplayName("카카오 로그인을 할 수 있다.")
    void signInWithKakaoTest() {
        //given
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
        String provider = "kakao";
        String idToken = "idToken";

        //when
        AuthTokenResponse result = authService.signIn(provider, idToken);

        //then
        assertThat(result.getAccessToken()).isEqualTo("accessToken");
        assertThat(result.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("구글 로그인을 할 수 있다.")
    void signInWithGoogleTest() {
        //given
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
        String provider = "google";
        String idToken = "idToken";

        //when
        AuthTokenResponse result = authService.signIn(provider, idToken);

        //then
        assertThat(result.getAccessToken()).isEqualTo("accessToken");
        assertThat(result.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("provider가 카카오나 구글이 아닐 시 에러를 던진다.")
    void signInErrorTest() {
        //given
        String provider = "abcd";
        String idToken = "idToken";
        //when
        //then
        assertThatThrownBy(() -> {
            authService.signIn(provider, idToken);
        }).isInstanceOf(CustomException.class).hasMessage("유효하지 않은 providerName 입니다.");
    }


    @Test
    @DisplayName("토큰 재발급을 할 수 있다.")
    void refreshTest() {
        //given
        BDDMockito.given(jwtTokenProvider.validateRefreshToken("refreshToken"))
                .willReturn(true);
        BDDMockito.given(jwtTokenProvider.getUserIdFromRefreshToken("refreshToken"))
                .willReturn(1L);
        BDDMockito.given(jwtTokenProvider.generateAccessToken(any(User.class)))
                .willReturn("accessToken");
        BDDMockito.given(jwtTokenProvider.generateRefreshToken(any(User.class)))
                .willReturn("refreshToken");
        String token = "refreshToken";

        //when
        AuthTokenResponse result = authService.refresh(token);

        //then
        assertThat(result.getAccessToken()).isEqualTo("accessToken");
        assertThat(result.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("토큰이 일치하지 않으면 에러를 던진다.")
    void refreshErrorTest() {
        //given
        String token = "refreshToken2";
        //when
        //then
        assertThatThrownBy(() -> {
            authService.refresh(token);
        }).isInstanceOf(CustomException.class).hasMessage("토큰이 유효하지 않습니다.");
    }

}