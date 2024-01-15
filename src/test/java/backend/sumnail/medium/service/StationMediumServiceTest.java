package backend.sumnail.medium.service;


import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.domain.station.repository.StationRepository;
import backend.sumnail.domain.station.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
//중형 테스트
@SpringBootTest
class StationMediumServiceTest {

    @Autowired
    StationService stationService;

    @Autowired
    StationRepository stationRepository;
    @Test
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