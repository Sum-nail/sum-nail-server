package backend.sumnail.domain.auth.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthKakaoLoginDto {
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class KakaoAccount {
        private Profile profile;
        private String email;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public static class Profile {
            private String nickname;

            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;
        }
    }
}
