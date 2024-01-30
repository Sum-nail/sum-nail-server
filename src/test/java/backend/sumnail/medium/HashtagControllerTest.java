package backend.sumnail.medium;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/hashtag-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class HashtagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("findAllHashtags로 조회하였을때, 200 상태를 반환한다.")
    void findAllHashtagsResponse() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/v1/hashtags"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("findHashtags로 조회하였을때, 모든 해시태그 정보들을 반환한다")
    void findAllHashtags() throws Exception {
        //given
        String expectedHashtag = "귀여운";
        //when
        //then
        mockMvc.perform(get("/v1/hashtags"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hashtags[0]").value(expectedHashtag))
                .andExpect(jsonPath("$.hashtags.length()").value(2));
    }
}