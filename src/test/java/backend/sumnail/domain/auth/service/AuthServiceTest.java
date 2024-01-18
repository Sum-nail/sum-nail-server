package backend.sumnail.domain.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount.Profile;
import backend.sumnail.domain.auth.controller.dto.response.AuthTokenResponse;
import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import backend.sumnail.domain.refresh_token.service.RefreshTokenService;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.mock.FakeRefreshTokenRepository;
import backend.sumnail.mock.FakeUserRepository;
import backend.sumnail.mock.TestGoogleClient;
import backend.sumnail.mock.TestJwtTokenProvider;
import backend.sumnail.mock.TestKakaoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthServiceTest {
    private AuthService authService;

    @BeforeEach
    void init() {
        TestJwtTokenProvider testJwtTokenProvider = new TestJwtTokenProvider(1L);
        AuthKakaoLoginDto authKakaoLoginDto = AuthKakaoLoginDto.builder()
                .kakaoAccount(KakaoAccount.builder()
                        .profile(Profile.builder()
                                .nickname("썸네일")
                                .thumbnailImageUrl("https://guardian.co.uk/one")
                                .build())
                        .email("sed@yahoo.edu")
                        .build())
                .build();
        AuthGoogleLoginDto authGoogleLoginDto = AuthGoogleLoginDto.builder()
                .name("썸네일")
                .email("sed@yahoo.edu")
                .picture("https://guardian.co.uk/one")
                .build();
        TestKakaoClient testKakaoClient = new TestKakaoClient(authKakaoLoginDto);
        TestGoogleClient testGoogleClient = new TestGoogleClient(authGoogleLoginDto);
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        fakeUserRepository.save(User.createUserByGoogleLogin(authGoogleLoginDto));
        fakeUserRepository.save(User.createUserByKakaoLogin(authKakaoLoginDto));
        FakeRefreshTokenRepository fakeRefreshTokenRepository = new FakeRefreshTokenRepository();
        fakeRefreshTokenRepository.save(RefreshToken.createRefreshToken("sed@yahoo.edu",1L));
        this.authService = AuthService.builder()
                .jwtTokenProvider(testJwtTokenProvider)
                .googleClient(testGoogleClient)
                .kakaoClient(testKakaoClient)
                .userRepository(fakeUserRepository)
                .refreshTokenService(new RefreshTokenService(fakeRefreshTokenRepository))
                .build();
    }

    @Test
    @DisplayName("카카오 로그인을 할 수 있다.")
    void signInWithKakaoTest() {
        //given
        //when
        AuthTokenResponse result = authService.signIn("kakao", "idToken");

        //then
        assertThat(result.getAccessToken()).isEqualTo("sed@yahoo.edu");
        assertThat(result.getRefreshToken()).isEqualTo("sed@yahoo.edu");
    }

    @Test
    @DisplayName("구글 로그인을 할 수 있다.")
    void signInWithGoogleTest() {
        //given
        //when
        AuthTokenResponse result = authService.signIn("google","idToken");

        //then
        assertThat(result.getAccessToken()).isEqualTo("sed@yahoo.edu");
        assertThat(result.getRefreshToken()).isEqualTo("sed@yahoo.edu");
    }

    @Test
    @DisplayName("provider가 카카오나 구글이 아닐 시 에러를 던진다.")
    void signInErrorTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> {
            authService.signIn("abcd", "5diiQ5_zfqNzwEwkm2JIMyuoVIUQin6eqeUKPXPrAAABjRubkAItjdRiIM79qQ");
        }).isInstanceOf(CustomException.class).hasMessage("유효하지 않은 providerName 입니다.");
    }


    @Test
    @DisplayName("토큰 재발급을 할 수 있다.")
    void refreshTest() {
        //given
        //when
        AuthTokenResponse result = authService.refresh("sed@yahoo.edu");

        //then
        assertThat(result.getAccessToken()).isEqualTo("sed@yahoo.edu");
        assertThat(result.getRefreshToken()).isEqualTo("sed@yahoo.edu");
    }

    @Test
    @DisplayName("토큰이 일치하지 않으면 에러를 던진다.")
    void refreshErrorTest() {
        //given
        //when
        AuthTokenResponse result = authService.refresh("sed@yahoo.edu");

        //then
        assertThatThrownBy(() -> {
            authService.refresh("abc@yahoo.edu");
        }).isInstanceOf(CustomException.class).hasMessage("토큰이 유효하지 않습니다.");
    }

}