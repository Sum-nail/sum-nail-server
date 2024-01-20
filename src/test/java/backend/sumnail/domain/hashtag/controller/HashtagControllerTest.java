package backend.sumnail.domain.hashtag.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.*;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value= "/sql/hashtag-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class HashtagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("findAllHashtags로 조회하였을때, 200 상태를 리스폰스한다.")
    void findAllHashtagsResponse() throws Exception {
        mockMvc.perform(get("/v1/hashtags"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("findHashtags로 조회하였을때, 해시태그 정보들을 가지고 있다")
    void findAllHashtags() throws Exception {
        mockMvc.perform(get("/v1/hashtags"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hashtags[0]").value("귀여운"));
    }
}