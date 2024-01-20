package backend.sumnail.domain.station.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.*;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value= "/sql/station-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
class StationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    @DisplayName("findStationByKeywords로 조회하였을때, 200 상태를 반환한다.")
    void findStationByKeywordsResponse() throws Exception {
        mockMvc.perform(get("/v1/stations?keyword=서울역"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("findStationByKeywords로 조회하였을때, 키워드를 포함한 지하철역 정보들을 반환한다")
    void findStationByKeywords() throws Exception {
        mockMvc.perform((get("/v1/stations?keyword=서울역")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stationName").value("서울역"))
                .andExpect(jsonPath("$[0].stationLine[0]").value("01호선"));
    }

    @Test
    @DisplayName("findStationByKeywords로 조회하였을때, 키워드가 없을때는 모든 지하철역 정보들을 반환한다")
    void findStationWithoutKeywords() throws Exception {
        mockMvc.perform((get("/v1/stations?keyword=")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}