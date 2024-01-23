package backend.sumnail.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.service.HashtagService;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.service.NailShopService;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.nail_shop_hashtag.service.NailShopHashtagService;
import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.recentsearch.service.RecentSearchService;
import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import backend.sumnail.domain.user_nail_shop.service.UserNailShopService;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.mock.FakeHashtagRepository;
import backend.sumnail.mock.FakeNailShopHashtagRepository;
import backend.sumnail.mock.FakeNailShopRepository;
import backend.sumnail.mock.FakeRecentSearchRepository;
import backend.sumnail.mock.FakeUserNailShopRepository;
import backend.sumnail.mock.FakeUserRepository;
import backend.sumnail.mock.TestClockHolder;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void init() {
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        FakeUserNailShopRepository fakeUserNailShopRepository = new FakeUserNailShopRepository();
        FakeNailShopRepository fakeNailShopRepository = new FakeNailShopRepository();
        FakeRecentSearchRepository fakeRecentSearchRepository = new FakeRecentSearchRepository();
        FakeHashtagRepository fakeHashtagRepository = new FakeHashtagRepository();
        FakeNailShopHashtagRepository fakeNailShopHashtagRepository = new FakeNailShopHashtagRepository();
        this.userService = UserService.builder()
                .userRepository(fakeUserRepository)
                .userNailShopService(new UserNailShopService(fakeUserNailShopRepository))
                .nailShopService(new NailShopService(
                        fakeNailShopRepository,
                        new HashtagService(fakeHashtagRepository,
                                new NailShopHashtagService(fakeNailShopHashtagRepository))
                ))
                .recentSearchService(new RecentSearchService(fakeRecentSearchRepository, fakeUserRepository))
                .build();

        User user1 = User.builder()
                .id(1L)
                .name("썸네일")
                .email("sed@yahoo.edu")
                .profileImage("https://guardian.co.uk/one")
                .build();
        fakeUserRepository.save(user1);

        User user2 = User.builder()
                .id(2L)
                .name("썸네일2")
                .email("abc@yahoo.edu")
                .profileImage("https://guardian.co.uk/one")
                .build();
        fakeUserRepository.save(user2);

        NailShop nailShop = NailShop.builder()
                .id(1L)
                .location("서울시 중구")
                .name("썸네일네일샵")
                .titleImage("http://zoom.us?g=1")
                .build();
        fakeNailShopRepository.save(nailShop);

        fakeUserNailShopRepository.save(UserNailShop.builder()
                .nailShop(nailShop)
                .user(user1)
                .build());

        Hashtag hashtag = Hashtag.builder()
                .id(1L)
                .hashtagName("심플한")
                .build();
        fakeHashtagRepository.save(hashtag);

        fakeNailShopHashtagRepository.save(NailShopHashtag.builder()
                .nailShop(nailShop)
                .hashtag(hashtag)
                .build());

        fakeRecentSearchRepository.save(
                RecentSearch.createRecentSearch(user1, "배방", new TestClockHolder("2002-01-29 16:19:00.000000"))
        );
        fakeRecentSearchRepository.save(
                RecentSearch.createRecentSearch(user1, "외대앞", new TestClockHolder("2002-02-29 16:19:00.000000"))
        );

    }

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
        assertThat(result.get(0).getHashtags()).isEqualTo(List.of("심플한"));
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
        assertThat(result.get(0).getHashtags()).isEqualTo(List.of("심플한"));
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