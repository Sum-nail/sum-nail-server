package backend.sumnail.domain.refresh_token.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefreshTokenTest {
    @Test
    @DisplayName("refreshToken과 keyUserId로 RefreshToken을 만들 수 있다.")
    public void createRefreshTokenTest() {
        //given
        //when
        RefreshToken refreshToken = RefreshToken.createRefreshToken("refreshToken", 1L);

        //then
        assertThat(refreshToken.getRefreshToken()).isEqualTo("refreshToken");
        assertThat(refreshToken.getKeyUserId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("refreshToken과 keyUserId로 RefreshToken을 만들 수 있다.")
    public void updateTokenTest() {
        //given
        RefreshToken refreshToken = RefreshToken.builder()
                .keyUserId(1L)
                .refreshToken("refreshToken")
                .build();

        //when
        refreshToken.updateToken("refreshToken2");


        //then
        assertThat(refreshToken.getRefreshToken()).isEqualTo("refreshToken2");
        assertThat(refreshToken.getKeyUserId()).isEqualTo(1L);
    }

}