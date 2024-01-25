package backend.sumnail.domain.refresh_token.repository;

import backend.sumnail.domain.refresh_token.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public Optional<RefreshToken> findByKeyUserId(Long keyUserId) {
        return refreshTokenJpaRepository.findByKeyUserId(keyUserId);
    }

    @Override
    public void save(RefreshToken newRefreshToken) {
        refreshTokenJpaRepository.save(newRefreshToken);
    }


}
