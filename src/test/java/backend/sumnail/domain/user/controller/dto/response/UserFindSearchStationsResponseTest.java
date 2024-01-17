package backend.sumnail.domain.user.controller.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserFindSearchStationsResponseTest {

    @Test
    @DisplayName("RecentSearch 객체 리스트로 응답을 생성할 수 있다.")
    public void fromTest(){
        //given
        User user = User.builder()
                .name("썸네일")
                .email("sed@yahoo.edu")
                .profileImage("https://guardian.co.uk/one")
                .build();

        List<RecentSearch> recentSearches = new ArrayList<>();
        RecentSearch recentSearch1 = RecentSearch.builder()
                .user(user)
                .station("배방")
                .build();
        RecentSearch recentSearch2 = RecentSearch.builder()
                .user(user)
                .station("외대앞")
                .build();
        recentSearches.add(recentSearch1);
        recentSearches.add(recentSearch2);

        //when
        UserFindSearchStationsResponse userFindSearchStationsResponse = UserFindSearchStationsResponse.from(recentSearches);

        //then
        assertThat(userFindSearchStationsResponse.getStations()).isEqualTo(List.of("배방","외대앞"));

    }

}