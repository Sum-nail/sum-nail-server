package backend.sumnail.domain.user.entity;

import static org.assertj.core.api.Assertions.assertThat;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount;
import backend.sumnail.domain.auth.controller.dto.AuthKakaoLoginDto.KakaoAccount.Profile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    @DisplayName("AuthGoogleLoginDto로 유저를 만들 수 있다.")
    public void createUserByGoogleLoginTest() {
        //given
        AuthGoogleLoginDto authGoogleLoginDto = AuthGoogleLoginDto.builder()
                .name("썸네일")
                .email("sed@yahoo.edu")
                .picture("https://guardian.co.uk/one")
                .build();

        //when
        User user = User.createUserByGoogleLogin(authGoogleLoginDto);

        //then
        assertThat(user.getName()).isEqualTo("썸네일");
        assertThat(user.getEmail()).isEqualTo("sed@yahoo.edu");
        assertThat(user.getProfileImage()).isEqualTo("https://guardian.co.uk/one");
    }

    @Test
    @DisplayName("AuthKakaoLoginDto로 유저를 만들 수 있다.")
    public void createUserByKakaoLoginTest() {
        //given
        Profile profile = Profile.builder()
                .nickname("썸네일")
                .thumbnailImageUrl("https://guardian.co.uk/one")
                .build();
        KakaoAccount kakaoAccount = KakaoAccount.builder()
                .profile(profile)
                .email("sed@yahoo.edu")
                .build();
        AuthKakaoLoginDto authKakaoLoginDto = AuthKakaoLoginDto.builder()
                .kakaoAccount(kakaoAccount)
                .build();

        //when
        User user = User.createUserByKakaoLogin(authKakaoLoginDto);

        //then
        assertThat(user.getName()).isEqualTo("썸네일");
        assertThat(user.getEmail()).isEqualTo("sed@yahoo.edu");
        assertThat(user.getProfileImage()).isEqualTo("https://guardian.co.uk/one");

    }


}