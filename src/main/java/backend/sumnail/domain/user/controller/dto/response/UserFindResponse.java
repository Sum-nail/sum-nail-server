package backend.sumnail.domain.user.controller.dto.response;

import backend.sumnail.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserFindResponse {
    private final String name;
    private final String email;
    private final String profileImage;

    public static UserFindResponse from(User user) {
        return UserFindResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .build();
    }

}
