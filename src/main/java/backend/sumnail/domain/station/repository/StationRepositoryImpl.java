package backend.sumnail.domain.station.repository;

import backend.sumnail.domain.station.entity.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StationRepositoryImpl implements StationRepository {

    private final StationJpaRepository stationJpaRepository;

    @Override
    public Optional<Station> findByStationName(String stationName) {
        return stationJpaRepository.findByStationName(stationName);
    }

    @Override
    public List<Station> findByStationNameContaining(String stationName) {
        return stationJpaRepository.findByStationNameContaining(stationName);
    }
}
