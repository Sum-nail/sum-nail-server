package backend.sumnail.mock;

import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import backend.sumnail.domain.auth.service.helper.KakaoClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestKakaoClient implements KakaoClient {
    private final AuthKakaoLoginDto authKakaoLoginDto;

    @Override
    public AuthKakaoLoginDto getUserInfo(String accessToken) {
        return authKakaoLoginDto;
    }
}
