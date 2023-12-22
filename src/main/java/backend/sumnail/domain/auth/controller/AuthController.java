package backend.sumnail.domain.auth.controller;

import backend.sumnail.domain.auth.controller.dto.request.AuthLoginRequest;
import backend.sumnail.domain.auth.controller.dto.request.AuthRefreshRequest;
import backend.sumnail.domain.auth.controller.dto.response.AuthTokenResponse;
import backend.sumnail.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 소셜 로그인 ( 구글, 카카오 )
     */
    @PostMapping("signin")
    public ResponseEntity<AuthTokenResponse> login(@RequestBody AuthLoginRequest request) {
        AuthTokenResponse response = authService.signIn(request.getProvider(), request.getIdToken());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 토큰 리프레시
     */
    @PostMapping("refresh")
    public ResponseEntity<AuthTokenResponse> refresh(@RequestBody AuthRefreshRequest request) {
        AuthTokenResponse response = authService.refresh(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
