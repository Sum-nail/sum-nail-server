package backend.sumnail.domain.refresh_token.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.mock.FakeRefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class RefreshTokenServiceTest {

    private RefreshTokenService refreshTokenService;

    @BeforeEach
    void init() {
        FakeRefreshTokenRepository fakeRefreshTokenRepository = new FakeRefreshTokenRepository();
        this.refreshTokenService = RefreshTokenService.builder()
                .refreshTokenRepository(fakeRefreshTokenRepository)
                .build();
        fakeRefreshTokenRepository.save(
                RefreshToken.builder()
                        .keyUserId(1L)
                        .refreshToken("refreshToken")
                        .build());
    }

    @Test
    @DisplayName("리프레시 토큰을 저장 할 수 있다.")
    void saveRefreshTokenTest() {
        //given
        String refreshToken = "refreshToken";
        Long keyUserId = 2L;

        //when
        refreshTokenService.saveRefreshToken(refreshToken,keyUserId);

        //then
        RefreshToken newRefreshToken = refreshTokenService.getByKeyUserId(2L);
        assertThat(newRefreshToken.getRefreshToken()).isEqualTo("refreshToken");
    }


    @Test
    @DisplayName("리프레시 토큰을 업데이트 할 수 있다.")
    void updateRefreshTokenTest() {
        //given
        String refreshToken = "refreshToken";
        Long keyUserId = 1L;

        //when
        refreshTokenService.saveRefreshToken(refreshToken,keyUserId);

        //then
        RefreshToken updatedRefreshToken = refreshTokenService.getByKeyUserId(1L);
        assertThat(updatedRefreshToken.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("UserId에 해당하는 refreshToken을 가져올 수 있다.")
    void getByKeyUserIdTest() {
        //given
        Long keyUserId = 1L;

        //when
        RefreshToken refreshToken = refreshTokenService.getByKeyUserId(keyUserId);

        //then
        assertThat(refreshToken.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("UserId에 해당하는 refreshToken이 없다면 에러를 던진다.")
    void getByKeyUserIdErrorTest() {
        //given
        Long keyUserId = 2L;

        //when
        //then
        assertThatThrownBy(() -> {
            refreshTokenService.getByKeyUserId(keyUserId);
        }).isInstanceOf(CustomException.class).hasMessage("토큰이 유효하지 않습니다.");
    }


}