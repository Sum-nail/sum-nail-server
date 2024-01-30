package backend.sumnail.domain.auth.service;

import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import backend.sumnail.domain.auth.controller.dto.response.AuthTokenResponse;
import backend.sumnail.domain.auth.entity.Provider;
import backend.sumnail.domain.auth.service.helper.GoogleClient;
import backend.sumnail.domain.auth.service.helper.KakaoClient;
import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import backend.sumnail.domain.refresh_token.service.RefreshTokenService;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user.service.port.UserRepository;
import backend.sumnail.global.config.jwt.JwtTokenProvider;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Builder
@Transactional
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final GoogleClient googleClient;
    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    public AuthTokenResponse signIn(String provider, String idToken) {
        User user = signInByProvider(provider, idToken);

        User findUser = userRepository.findByEmail(user.getEmail())
                        .orElse(null);


        if (findUser == null) { // 최초 로그인이라면 회원가입 시키기
            userRepository.save(user);
        }

        User newUser = userRepository.getByEmail(user.getEmail());

        return createAndSaveToken(newUser);

    }


    public AuthTokenResponse refresh(String token) {
        String refreshToken = token.replace("Bearer", "");

        //refreshToken 유효성 확인
        jwtTokenProvider.validateRefreshToken(refreshToken);

        // userId 는 userPK
        Long userId = jwtTokenProvider.getUserIdFromRefreshToken(refreshToken);

        RefreshToken findRefreshToken = refreshTokenService.getByKeyUserId(userId);

        if (!refreshToken.equals(findRefreshToken.getRefreshToken())) {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }

        User user = userRepository.getById(userId);
        return createAndSaveToken(user);
    }


    private User signInByProvider(String provider, String idToken) {
        if (!provider.equals(Provider.GOOGLE.getProviderName()) && !provider.equals(Provider.KAKAO.getProviderName())) {
            throw new CustomException(ErrorCode.INVALID_PROVIDER_NAME);
        }

        // 구글 로그인
        if (Provider.GOOGLE.getProviderName().equals(provider)) {
            return User.createUserByGoogleLogin(googleClient.getUserInfo(idToken));
        }

        // 카카오 로그인
        AuthKakaoLoginDto userInfo = kakaoClient.getUserInfo("Bearer " + idToken);
        return User.createUserByKakaoLogin(userInfo);
    }


    private AuthTokenResponse createAndSaveToken(User user) {
        System.out.println("^^^^"+user.getId());
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        System.out.println("$$$");

        refreshTokenService.saveRefreshToken(refreshToken, user.getId());

        return AuthTokenResponse.of(accessToken, refreshToken);
    }


}
