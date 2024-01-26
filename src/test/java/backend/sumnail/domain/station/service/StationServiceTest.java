package backend.sumnail.domain.station.service;

import static org.assertj.core.api.Assertions.assertThat;

import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.mock.FakeStationRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class StationServiceTest {

    private StationService stationService;

    @BeforeEach
    void init() {
        FakeStationRepository fakeStationRepository = new FakeStationRepository();
        this.stationService = StationService.builder()
                .stationRepository(fakeStationRepository)
                .build();
        fakeStationRepository.save(fakeStationRepository.save(Station.builder()
                .id(1L)
                .line(List.of("1호선", "2호선"))
                .stationName("서울역")
                .build()));
        fakeStationRepository.save(fakeStationRepository.save(Station.builder()
                .id(2L)
                .line(List.of("2호선"))
                .stationName("서울대입구역")
                .build()));
    }

    @Test
    @DisplayName("findStations로 해당 이름을 포함하고 있는 지하철역 전체 조회가 가능하다")
    void findStations() {
        //given
        String stationName = "서울역";
        List<String> stationLines = List.of("1호선", "2호선");

        //when
        List<StationFindAllResponse> stationResponses = stationService.findStations("서울");

        //then
        assertThat(stationResponses.get(0).getStationLine()).isEqualTo(stationLines);
        assertThat(stationResponses.get(0).getStationName()).isEqualTo(stationName);
    }
}