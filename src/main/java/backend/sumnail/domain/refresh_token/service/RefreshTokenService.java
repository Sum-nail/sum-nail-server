package backend.sumnail.domain.refresh_token.service;

import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import backend.sumnail.domain.refresh_token.repository.RefreshTokenRepository;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String refreshToken, Long keyUserId) {

        RefreshToken newRefreshToken = RefreshToken.createRefreshToken(refreshToken, keyUserId);

        // 기존 토큰이 있으면 업데이트하고, 없으면 새로 생성하여 저장
        refreshTokenRepository.findByKeyUserId(keyUserId)
                .ifPresentOrElse(
                        (findRefreshToken) -> findRefreshToken.updateToken(refreshToken),
                        () -> refreshTokenRepository.save(newRefreshToken)
                );
    }

    public RefreshToken getByKeyUserId(Long userId) {

        return refreshTokenRepository.findByKeyUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_INVALID));
    }
}