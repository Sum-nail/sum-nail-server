package backend.sumnail.domain.station.service;

import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.mock.FakeStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;



class StationServiceTest {

    private StationService stationService;

    @BeforeEach
    void init(){
        FakeStationRepository fakeStationRepository=new FakeStationRepository();
        this.stationService=StationService.builder()
                .stationRepository(fakeStationRepository)
                .build();
        fakeStationRepository.save(fakeStationRepository.save(Station.builder()
                .id(1L)
                .line(List.of("1호선","2호선"))
                .stationName("서울역")
                .build()));
        fakeStationRepository.save(fakeStationRepository.save(Station.builder()
                .id(2L)
                .line(List.of("2호선"))
                .stationName("서울대입구역")
                .build()));
    }
    @Test
    @DisplayName("findStations 검색명을 포함하는 역들을 조회할 수 있다.")
    void findStations() {
        //given
        String stationName="서울역";
        List<String> stationLines=List.of("1호선","2호선");

        //when
        List<StationFindAllResponse> stationResponses=stationService.findStations("서울");

        //then
        assertThat(stationResponses)
                .extracting("stationName","stationLine")
                .contains(tuple(stationName,stationLines));
    }
}