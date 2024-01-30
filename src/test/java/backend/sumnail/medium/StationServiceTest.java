package backend.sumnail.medium;


import static org.assertj.core.api.Assertions.assertThat;

import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.domain.station.service.port.StationRepository;
import backend.sumnail.domain.station.service.StationService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

//중형 테스트
@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/station-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class StationServiceTest {

    @Autowired
    StationService stationService;

    @Autowired
    StationRepository stationRepository;

    @Test
    @DisplayName("findStations로 해당 이름을 포함하고 있는 지하철역 전체 조회가 가능하다")
    void findStations() {
        //given
        Station station = stationRepository.findByStationName("서울역").orElseThrow();
        String stationName = station.getStationName();
        List<String> stationLines = station.getLine();

        //when
        List<StationFindAllResponse> stationResponses = stationService.findStations(stationName);

        //then
        assertThat(stationResponses.get(0).getStationLine()).isEqualTo(stationLines);
        assertThat(stationResponses.get(0).getStationName()).isEqualTo(stationName);
    }
}