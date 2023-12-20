package backend.sumnail.domain.refresh_token.repository;

import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    @Override
    public Optional<RefreshToken> findByKeyLoginId(String loginId) {
        return refreshTokenJpaRepository.findByKeyLoginId(loginId);
    }
}
