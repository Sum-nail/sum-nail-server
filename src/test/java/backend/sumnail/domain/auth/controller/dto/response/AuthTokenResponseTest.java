package backend.sumnail.domain.auth.controller.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthTokenResponseTest {
    @Test
    @DisplayName("accessToken과 refreshToken으로 응답을 생성할 수 있다.")
    public void ofTest() {
        //given
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNyIsImlhdCI6MTcwNDE5MjMzMSwiZXhwIjoxNzA1NDAxOTMxfQ.04RTRdIdMbIDLyTZLK1H2rAsysYysDx3ClPkr7LTCk8";
        String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNyIsImlhdCI6MTcwNDE5MjMzMSwiZXhwIjoxNzA1NDAxOTMxfQ.04RTRdIdMbIDLyTZLK1H2rAsysYysDx3ClPkr7LTCk8";

        //when
        AuthTokenResponse authTokenResponse = AuthTokenResponse.of(accessToken, refreshToken);

        //then
        assertThat(authTokenResponse.getAccessToken()).isEqualTo(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNyIsImlhdCI6MTcwNDE5MjMzMSwiZXhwIjoxNzA1NDAxOTMxfQ.04RTRdIdMbIDLyTZLK1H2rAsysYysDx3ClPkr7LTCk8");
        assertThat(authTokenResponse.getRefreshToken()).isEqualTo(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNyIsImlhdCI6MTcwNDE5MjMzMSwiZXhwIjoxNzA1NDAxOTMxfQ.04RTRdIdMbIDLyTZLK1H2rAsysYysDx3ClPkr7LTCk8");
    }

}