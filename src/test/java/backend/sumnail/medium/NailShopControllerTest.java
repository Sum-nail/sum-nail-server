package backend.sumnail.medium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/nail_shop-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class NailShopControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getAllShops는 저장되어 있는 모든 NailShop 정보를 반환한다")
    void getAllShops() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/v1/nail-shops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("searchShops는 해당 station과 hashtag를 포함한 NailShop 정보를 반환한다")
    void searchShopsWithStationAndHashtag() throws Exception {
        //given
        String hashtags = "귀여운";
        String nailShopName = "귀여운 서울역 네일샵";
        //when
        //then
        mockMvc.perform(get("/v1/nail-shops/search?hashtags=귀여운&station=서울역"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nailShopName").value(nailShopName))
                .andExpect(jsonPath("$[0].hashtags[0]").value(hashtags));
    }

    @Test
    @DisplayName("searchShops는 Station 정보가 없을때 해당 hashtag만을 포함한 NailShop 정보를 반환한다")
    void searchShopsWithHashtag() throws Exception {
        //given
        String hashtags = "쿨한";
        String nailShopName = "쿨한 강남역 네일샵";
        //when
        //then
        mockMvc.perform(get("/v1/nail-shops/search?hashtags=쿨한&station="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nailShopName").value(nailShopName))
                .andExpect(jsonPath("$[0].hashtags[0]").value(hashtags));
    }

    @Test
    @DisplayName("searchShops는 hashtag 정보가 없을때 해당 station만을 포함한 NailShop 정보를 반환한다")
    void searchShopsWithStation() throws Exception {
        //given
        String hashtags = "귀여운";
        String nailShopName = "귀여운 서울역 네일샵";
        //when
        //then
        mockMvc.perform(get("/v1/nail-shops/search?hashtags=&station=서울역"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nailShopName").value(nailShopName))
                .andExpect(jsonPath("$[0].hashtags[0]").value(hashtags));
    }

    @Test
    @DisplayName("searchShopByID는 NailShop의 ID를 통해 해당 NailShop 정보를 반환한다")
    void searchShopById() throws Exception {
        //given
        Long nailShopId = 1L;
        String nailShopName = "귀여운 서울역 네일샵";
        String hashtags = "귀여운";
        //when
        //then
        mockMvc.perform(get("/v1/nail-shops/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nailShopId").value(nailShopId))
                .andExpect(jsonPath("$.nailShopName").value(nailShopName))
                .andExpect(jsonPath("$.hashtags[0]").value(hashtags));
    }

    @Test
    @DisplayName("searchShopByID는 존재하지 않는 NailShop의 ID로 검색할 경우 404 상태를 반환한다")
    void searchShopByIdWithNotExistId() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/v1/nail-shops/3"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("해당 네일샵 찾을 수 없습니다."));
    }
}