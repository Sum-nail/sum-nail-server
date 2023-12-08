package backend.sumnail.domain.station.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StationFindAllResponse {

    private String stationName;
    private String stationLine;
}
