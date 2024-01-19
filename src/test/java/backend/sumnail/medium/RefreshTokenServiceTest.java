package backend.sumnail.medium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import backend.sumnail.domain.refresh_token.service.RefreshTokenService;
import backend.sumnail.global.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/auth-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class RefreshTokenServiceTest {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Test
    @DisplayName("리프레시 토큰을 저장 할 수 있다.")
    void saveRefreshTokenTest() {
        //given

        //when
        refreshTokenService.saveRefreshToken("refreshToken", 2L);

        //then
        RefreshToken refreshToken = refreshTokenService.getByKeyUserId(2L);
        assertThat(refreshToken.getRefreshToken()).isEqualTo("refreshToken");
    }


    @Test
    @DisplayName("리프레시 토큰을 업데이트 할 수 있다.")
    void updateRefreshTokenTest() {
        //given

        //when
        refreshTokenService.saveRefreshToken("refreshToken", 1L);

        //then
        RefreshToken refreshToken = refreshTokenService.getByKeyUserId(1L);
        assertThat(refreshToken.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("UserId에 해당하는 refreshToken을 가져올 수 있다.")
    void getByKeyUserIdTest() {
        //given
        //when
        RefreshToken refreshToken = refreshTokenService.getByKeyUserId(1L);

        //then
        assertThat(refreshToken.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("UserId에 해당하는 refreshToken이 없다면 에러를 던진다.")
    void getByKeyUserIdErrorTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> {
            refreshTokenService.getByKeyUserId(2L);
        }).isInstanceOf(CustomException.class).hasMessage("토큰이 유효하지 않습니다.");
    }



}