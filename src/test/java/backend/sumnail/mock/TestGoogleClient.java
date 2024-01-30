package backend.sumnail.mock;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import backend.sumnail.domain.auth.service.helper.GoogleClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestGoogleClient implements GoogleClient {

    private final AuthGoogleLoginDto authGoogleLoginDto;

    @Override
    public AuthGoogleLoginDto getUserInfo(String idToken) {
        return authGoogleLoginDto;
    }
}
