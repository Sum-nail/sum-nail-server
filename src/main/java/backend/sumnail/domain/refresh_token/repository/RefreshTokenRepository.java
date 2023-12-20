package backend.sumnail.domain.refresh_token.repository;

import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import backend.sumnail.domain.user.entity.User;
import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByKeyLoginId(String loginId);
}
