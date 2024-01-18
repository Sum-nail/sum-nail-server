package backend.sumnail.global.config.jwt;

import backend.sumnail.domain.user.entity.User;

public interface JwtTokenProvider {
    // accessToken 생성
    String generateAccessToken(User user);

    // RefreshToken 생성
    String generateRefreshToken(User user);
    // accessToken에서 userPk 추출
    Long getUserIdFromAccessToken(String token);

    // refreshToken에서 userPK 추출
    Long getUserIdFromRefreshToken(String token);

    // refresh 토큰의 유효성 + 만료일자 확인 -> 유효하면 true 리턴
    Boolean validateRefreshToken(String token);
}
