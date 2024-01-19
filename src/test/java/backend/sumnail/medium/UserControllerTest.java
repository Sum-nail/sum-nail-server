package backend.sumnail.medium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.recentsearch.repository.RecentSearchRepository;
import backend.sumnail.domain.user.controller.dto.request.RecentSearchSaveRequest;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import backend.sumnail.domain.user_nail_shop.repository.UserNailShopRepository;
import backend.sumnail.global.config.jwt.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/user-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class UserControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserNailShopRepository userNailShopRepository;
    @Autowired
    private RecentSearchRepository recentSearchRepository;

    @Test
    @DisplayName("자신의 프로필을 조회할 수 있다.")
    void findUserTest() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
                .name("썸네일")
                .email("sed@yahoo.edu")
                .profileImage("https://guardian.co.uk/one")
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/profile")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("썸네일"))
                .andExpect(jsonPath("$.email").value("sed@yahoo.edu"))
                .andExpect(jsonPath("$.profileImage").value("https://guardian.co.uk/one"));
    }

    @Test
    @DisplayName("존재하지 않는 유저의 아이디로 요청할 경우 404 응답을 받는다")
    void findUserErrorTest() throws Exception {
        //given
        User user = User.builder()
                .id(102934563L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/profile")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.error").value("해당 유저를 찾을 수 없습니다."));

    }

    @Test
    @DisplayName("저장한 네일샵을 조회할 수 있다.")
    void findAllNailShopsUserTest() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/nail-shops")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nailShopId").value(1))
                .andExpect(jsonPath("$[0].nailShopName").value("썸네일네일샵"))
                .andExpect(jsonPath("$[0].location").value("서울시 중구"))
                .andExpect(jsonPath("$[0].titleImage").value("http://zoom.us?g=1"))
                .andExpect(jsonPath("$[0].hashtags", containsInAnyOrder("심플한", "화려한")));
    }

    @Test
    @DisplayName("저장한 네일샵이 없을 경우 빈 배열을 반환한다.")
    void findAllNailShopsUserEmptyTest() throws Exception {
        //given
        User user = User.builder()
                .id(2L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/nail-shops")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @DisplayName("네일샵을 저장할 수 있다.")
    void saveNailShopUserTest() throws Exception {
        //given
        User user = User.builder()
                .id(2L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(post("/v1/user/nail-shops/1")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isCreated());

        List<UserNailShop> userNailShops = userNailShopRepository.findByUserId(2);
        assertThat(userNailShops.size()).isEqualTo(1);
        assertThat(userNailShops.get(0).getUser().getId()).isEqualTo(2);
        assertThat(userNailShops.get(0).getNailShop().getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("네일샵을 중복해서 저장하면 409 응답을 받는다.")
    void saveNailShopUserConflictTest() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(post("/v1/user/nail-shops/1")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value("CONFLICT"))
                .andExpect(jsonPath("$.error").value("이미 저장한 네일샵입니다."));
    }

    @Test
    @DisplayName("네일샵 저장을 취소할 수 있다.")
    void deleteNailShopUserTest() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(delete("/v1/user/nail-shops/1")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isNoContent());

        List<UserNailShop> userNailShops = userNailShopRepository.findByUserId(2);
        assertThat(userNailShops.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("저장한 적 없는 네일샵을 취소하면 404 응답을 받는다.")
    void deleteNailShopUserNotFoundTest() throws Exception {
        //given
        User user = User.builder()
                .id(2L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(delete("/v1/user/nail-shops/1")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.error").value("저장한 적 없는 네일샵입니다."));
    }


    @Test
    @DisplayName("지하철 역 검색내역을 조회할 수 있다.")
    void findSearchStationsUserTest() throws Exception {

        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(get("/v1/user/search-station-history")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stations", containsInAnyOrder("외대앞", "배방")));
    }

    @Test
    @DisplayName("지하철 역 검색 기록을 전체 삭제할 수 있다.")
    void deleteSearchStationsUserTest() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        //when
        //then
        mockMvc.perform(delete("/v1/user/search-station-history")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails)))
                .andExpect(status().isNoContent());

        List<RecentSearch> recentSearches = recentSearchRepository.findByUserId(1);
        assertThat(recentSearches.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("새로운 지하철역 검색기록을 추가할 수 있다.")
    void saveSearchStationsUserTest() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        RecentSearchSaveRequest request = RecentSearchSaveRequest.builder()
                .stationName("합정")
                .build();
        //when
        //then
        mockMvc.perform(post("/v1/user/search-station-history")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        List<RecentSearch> recentSearches = recentSearchRepository.findByUserId(1);
        assertThat(recentSearches.size()).isEqualTo(3);
        assertThat(recentSearches.get(0).getStation()).isEqualTo("배방");
        assertThat(recentSearches.get(1).getStation()).isEqualTo("외대앞");
        assertThat(recentSearches.get(2).getStation()).isEqualTo("합정");
    }

    @Test
    @DisplayName("같은 지하철역을 한번 더 검색하면 이전 검색내역이 삭제된다.")
    void saveSearchStationsUserDuplicateTest() throws Exception {
        //given
        User user = User.builder()
                .id(1L)
                .build();
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        RecentSearchSaveRequest request = RecentSearchSaveRequest.builder()
                .stationName("배방")
                .build();
        //when
        //then
        mockMvc.perform(post("/v1/user/search-station-history")
                        .with(SecurityMockMvcRequestPostProcessors.user(principalDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        List<RecentSearch> recentSearches = recentSearchRepository.findByUserId(1);
        assertThat(recentSearches.size()).isEqualTo(2);
        assertThat(recentSearches.get(0).getStation()).isEqualTo("외대앞");
        assertThat(recentSearches.get(1).getStation()).isEqualTo("배방");
    }


}