package backend.sumnail.domain.auth.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthLoginRequest {
    private String provider;
    private String idToken;
}
