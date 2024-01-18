package backend.sumnail.mock;

import backend.sumnail.domain.user.entity.User;
import backend.sumnail.global.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestJwtTokenProvider implements JwtTokenProvider {
    private final Long userId;

    @Override
    public String generateAccessToken(User user) {
        return user.getEmail();
    }

    @Override
    public String generateRefreshToken(User user) {
        return user.getEmail();
    }

    @Override
    public Long getUserIdFromAccessToken(String token) {
        return userId;
    }

    @Override
    public Long getUserIdFromRefreshToken(String token) {
        return userId;
    }

    @Override
    public Boolean validateRefreshToken(String token) {
        return true;
    }
}
