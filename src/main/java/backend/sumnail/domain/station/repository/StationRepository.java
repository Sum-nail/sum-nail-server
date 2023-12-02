package backend.sumnail.domain.station.repository;

import backend.sumnail.domain.station.entity.Station;

import java.util.List;
import java.util.Optional;

public interface StationRepository {
    Optional<Station> findByStationName(String stationName);

    List<Station> findByStationNameContaining(String stationName);
}
