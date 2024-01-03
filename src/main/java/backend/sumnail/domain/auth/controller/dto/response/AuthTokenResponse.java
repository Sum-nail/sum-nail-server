package backend.sumnail.domain.auth.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthTokenResponse {
    private String accessToken;

    private String refreshToken;

    public static AuthTokenResponse of(String accessToken, String refreshToken) {
        return AuthTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}