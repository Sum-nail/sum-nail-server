package backend.sumnail.domain.auth.service.helper;

import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "kakaoClient", url = "https://kapi.kakao.com", configuration = FeignClientConfig.class)
public interface KakaoClient {

    @GetMapping("/v2/user/me")
    AuthKakaoLoginDto getUserInfo(@RequestHeader("Authorization") String accessToken);  // 카카오의 idToken = accessToken
}

