package backend.sumnail.domain.station.service;

import backend.sumnail.domain.station.controller.dto.response.StationFindAllResponse;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.domain.station.repository.StationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StationService {

    private final StationRepository stationRepository;

    public List<StationFindAllResponse> findStations(String keywords) {
        List<Station> stations = stationRepository.findByStationNameContaining(keywords);

        List<StationFindAllResponse> responses = stations
                .stream()
                .map(StationFindAllResponse::from)
                .toList();

        return responses;
    }
}
