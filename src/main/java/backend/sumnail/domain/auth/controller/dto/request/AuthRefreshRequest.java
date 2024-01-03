package backend.sumnail.domain.auth.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthRefreshRequest {
    private String refreshToken;
}
