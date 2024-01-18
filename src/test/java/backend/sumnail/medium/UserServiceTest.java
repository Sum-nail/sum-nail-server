package backend.sumnail.medium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.service.UserService;
import backend.sumnail.global.exception.CustomException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/user-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("findUser는 특정 유저를 찾아올 수 있다.")
    void findUserTest() {
        //given
        //when
        UserFindResponse result = userService.findUser(1L);

        //then
        assertThat(result.getName()).isEqualTo("썸네일");
        assertThat(result.getEmail()).isEqualTo("sed@yahoo.edu");
        assertThat(result.getProfileImage()).isEqualTo("https://guardian.co.uk/one");
    }

    @Test
    @DisplayName("findAllNAilShopUser는 유저가 저장한 네일샵 전체를 찾아올 수 있다.")
    void findAllNailShopUserTest() {
        //given
        //when
        List<UserFindNailShopResponse> result = userService.findAllNailShopsUser(1L);

        //then
        assertThat(result.get(0).getNailShopId()).isEqualTo(1L);
        assertThat(result.get(0).getNailShopName()).isEqualTo("썸네일네일샵");
        assertThat(result.get(0).getLocation()).isEqualTo("서울시 중구");
        assertThat(result.get(0).getTitleImage()).isEqualTo("http://zoom.us?g=1");
        assertThat(result.get(0).getHashtags()).isEqualTo(List.of("심플한","화려한"));
    }

    @Test
    @DisplayName("saveNailShopUser를 이용해서 네일샵을 저장할 수 있다.")
    void saveNailShopUserTest() {
        //given
        //when
        userService.saveNailShopUser(2L, 1L);

        //then
        List<UserFindNailShopResponse> result = userService.findAllNailShopsUser(2L);
        assertThat(result.get(0).getNailShopId()).isEqualTo(1L);
        assertThat(result.get(0).getNailShopName()).isEqualTo("썸네일네일샵");
        assertThat(result.get(0).getLocation()).isEqualTo("서울시 중구");
        assertThat(result.get(0).getTitleImage()).isEqualTo("http://zoom.us?g=1");
        assertThat(result.get(0).getHashtags()).isEqualTo(List.of("심플한","화려한"));
    }

    @Test
    @DisplayName("이미 저장한 네일샵을 다시 저장하면 에러를 던진다.")
    void saveNailShopUserErrorTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> {
            userService.saveNailShopUser(1L, 1L);
        }).isInstanceOf(CustomException.class).hasMessage("이미 저장한 네일샵입니다.");
    }


    @Test
    @DisplayName("deleteNailShopUser를 이용해서 저장한 네일샵을 삭제할 수 있다.")
    void deleteNailShopUserTest() {
        //given
        //when
        userService.deleteNailShopUser(1L, 1L);

        //then
        List<UserFindNailShopResponse> result = userService.findAllNailShopsUser(2L);
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("저장하지 않은 네일샵을 삭제하면 에러를 던진다.")
    void deleteNailShopUserErrorTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> {
            userService.deleteNailShopUser(2L, 1L);
        }).isInstanceOf(CustomException.class).hasMessage("저장한 적 없는 네일샵입니다.");
    }

    @Test
    @DisplayName("findSearchStationUser를 이용해서 지하철 검색 내역을 조회 할 수 있다.")
    void findSearchStationUserTest() {
        //given
        //when
        UserFindSearchStationsResponse result = userService.findSearchStationsUser(1L);
        //then
        assertThat(result.getStations()).isEqualTo(List.of("외대앞", "배방"));
    }


    @Test
    @DisplayName("deleteSearchStationUser를 이용해서 지하철 검색 내역 전체를 삭제할 수 있다.")
    void deleteSearchStationUserTest() {
        //given
        //when
        userService.deleteSearchStationsUser(1L);
        //then
        UserFindSearchStationsResponse result = userService.findSearchStationsUser(1L);
        assertThat(result.getStations().size()).isEqualTo(0);
    }


}