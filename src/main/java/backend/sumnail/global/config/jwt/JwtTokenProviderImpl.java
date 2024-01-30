package backend.sumnail.global.config.jwt;

import backend.sumnail.domain.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProviderImpl implements JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.secret_refresh}")
    private String refreshSecretKey;

    // 객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public String generateAccessToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getId().toString()); // JWT payload에 저장되는 정보 단위
        Date issuedAt = new Date();
        Date TokenExpiresIn = new Date(issuedAt.getTime() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME);

        return buildToken(claims, issuedAt, TokenExpiresIn, secretKey);
    }

    @Override
    public String generateRefreshToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        Date issuedAt = new Date();
        Date TokenExpiresIn = new Date(issuedAt.getTime() + JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME);

        return buildToken(claims, issuedAt, TokenExpiresIn, refreshSecretKey);
    }

    @Override
    public Long getUserIdFromAccessToken(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject());
    }

    @Override
    public Long getUserIdFromRefreshToken(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .setSigningKey(refreshSecretKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject());
    }

    @Override
    public Boolean validateRefreshToken(String token) {
        try {
            Jwts.parser().setSigningKey(refreshSecretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtException("TOKEN_EXPIRED");
        } catch (JwtException e) {
            throw new JwtException("TOKEN_INVALID");
        }
    }

    private String buildToken(Claims claims, Date issuedAt, Date TokenExpiresIn, String key) {

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(issuedAt) // 토큰 발행 시간
                .setExpiration(TokenExpiresIn) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }


}