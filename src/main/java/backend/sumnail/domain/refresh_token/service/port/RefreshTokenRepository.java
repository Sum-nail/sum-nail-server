package backend.sumnail.domain.refresh_token.service.port;

import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByKeyUserId(Long userId);

    void save(RefreshToken refreshToken);

}
