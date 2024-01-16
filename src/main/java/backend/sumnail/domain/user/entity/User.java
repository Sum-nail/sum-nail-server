package backend.sumnail.domain.user.entity;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String profileImage;

    @Builder
    protected User(Long id, String name, String email, String profileImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public static User createUserByGoogleLogin(AuthGoogleLoginDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .profileImage(dto.getPicture())
                .build();
    }

    public static User createUserByKakaoLogin(AuthKakaoLoginDto dto) {
        return User.builder()
                .name(dto.getKakaoAccount().getProfile().getNickname())
                .email(dto.getKakaoAccount().getEmail())
                .profileImage(dto.getKakaoAccount().getProfile().getThumbnailImageUrl())
                .build();
    }
}
