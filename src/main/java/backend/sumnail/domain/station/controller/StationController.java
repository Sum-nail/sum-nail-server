package backend.sumnail.domain.station.controller;

import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;
    @GetMapping("")
    public ResponseEntity<List<StationFindAllResponse>> findStationByKeywords(@RequestParam String keyword){
        List<StationFindAllResponse> response=stationService.findStations(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
