package backend.sumnail.domain.station.service;

import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.domain.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StationService {

    private final StationRepository stationRepository;
    public List<StationFindAllResponse> findStations(String keywords){
        List<Station> stations=stationRepository.findByStationNameContaining(keywords);

        List<StationFindAllResponse> responses=stations
                .stream()
                .map(station -> StationFindAllResponse.from(station))
                .toList();

        return responses;
    }
}
