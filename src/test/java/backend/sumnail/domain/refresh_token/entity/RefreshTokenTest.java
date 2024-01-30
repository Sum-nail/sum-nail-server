package backend.sumnail.domain.refresh_token.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefreshTokenTest {
    @Test
    @DisplayName("refreshToken과 keyUserId로 RefreshToken을 만들 수 있다.")
    public void createRefreshTokenTest() {
        //given
        String refreshToken = "refreshToken";
        Long keyUserId = 1L;

        //when
        RefreshToken newRefreshToken = RefreshToken.createRefreshToken(refreshToken, keyUserId);

        //then
        assertThat(newRefreshToken.getRefreshToken()).isEqualTo("refreshToken");
        assertThat(newRefreshToken.getKeyUserId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("refreshToken과 keyUserId로 RefreshToken을 만들 수 있다.")
    public void updateTokenTest() {
        //given
        RefreshToken refreshToken = RefreshToken.builder()
                .keyUserId(1L)
                .refreshToken("refreshToken")
                .build();
        String updateToken = "refreshToken2";

        //when
        refreshToken.updateToken(updateToken);

        //then
        assertThat(refreshToken.getRefreshToken()).isEqualTo("refreshToken2");
        assertThat(refreshToken.getKeyUserId()).isEqualTo(1L);
    }

}