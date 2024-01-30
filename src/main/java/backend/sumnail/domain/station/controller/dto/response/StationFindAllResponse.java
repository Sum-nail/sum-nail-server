package backend.sumnail.domain.station.controller.dto.response;

import backend.sumnail.domain.station.entity.Station;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StationFindAllResponse {

    private String stationName;
    private List<String> stationLine;

    public static StationFindAllResponse from(Station station) {

        return StationFindAllResponse.builder()
                .stationName(station.getStationName())
                .stationLine(station.getLine())
                .build();
    }
}
