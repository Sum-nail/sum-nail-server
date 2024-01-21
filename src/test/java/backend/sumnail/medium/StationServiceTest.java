package backend.sumnail.medium;


import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.domain.station.repository.StationRepository;
import backend.sumnail.domain.station.service.StationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
//중형 테스트
@SpringBootTest
class StationServiceTest {

    @Autowired
    StationService stationService;

    @Autowired
    StationRepository stationRepository;
    @Test
    @DisplayName("findStations로 해당 이름을 포함하고 있는 지하철역 전체 조회가 가능하다")
    void findStations() {
        //given
        Station station=stationRepository.findByStationName("서울역").orElseThrow();
        String stationName=station.getStationName();
        List<String> stationLines=station.getLine();

        //when
        List<StationFindAllResponse> stationResponses=stationService.findStations(stationName);

        //then
        assertThat(stationResponses)
                .extracting("stationName","stationLine")
                .contains(tuple(stationName,stationLines));
    }
}