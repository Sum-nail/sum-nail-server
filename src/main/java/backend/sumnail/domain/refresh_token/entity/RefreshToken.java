package backend.sumnail.domain.refresh_token.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    @Column(length=600)
    private String refreshToken;

    private String keyUserId;

    @Builder
    public RefreshToken(String refreshToken, String keyUserId) {
        this.refreshToken = refreshToken;
        this.keyUserId = keyUserId;
    }

    public static RefreshToken createRefreshToken(String refreshToken, String keyUserId) {
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .keyUserId(keyUserId)
                .build();
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}