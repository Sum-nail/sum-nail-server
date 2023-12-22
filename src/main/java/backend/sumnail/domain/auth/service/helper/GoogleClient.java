package backend.sumnail.domain.auth.service.helper;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "googleClient", url = "https://oauth2.googleapis.com", configuration = FeignClientConfig.class)
public interface GoogleClient {

    @GetMapping("/tokeninfo")
    AuthGoogleLoginDto getUserInfo(@RequestParam("id_token") String idToken);
}
