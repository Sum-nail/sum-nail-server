package backend.sumnail.domain.user.controller.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import backend.sumnail.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserFindResponseTest {

    @Test
    @DisplayName("User로 응답을 생성할 수 있다.")
    public void fromTest(){
        //given
        User user = User.builder()
                .name("썸네일")
                .email("sed@yahoo.edu")
                .profileImage("https://guardian.co.uk/one")
                .build();

        //when
        UserFindResponse userFindResponse = UserFindResponse.from(user);

        //then
        assertThat(userFindResponse.getName()).isEqualTo("썸네일");
        assertThat(userFindResponse.getEmail()).isEqualTo("sed@yahoo.edu");
        assertThat(userFindResponse.getProfileImage()).isEqualTo("https://guardian.co.uk/one");

    }


}