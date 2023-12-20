package backend.sumnail.domain.user.entity;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String profileImage;

    @Builder
    public User(String name, String email, String profileImage) {
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public static User createUser(String name, String email, String profileImage) {
        return User.builder()
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .build();
    }

    public static User createUserByGoogleLogin(AuthGoogleLoginDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .profileImage(dto.getPicture())
                .build();
    }
}
