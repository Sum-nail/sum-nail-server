package backend.sumnail.domain.station.repository;

import backend.sumnail.domain.station.entity.Station;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationJpaRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByStationName(String stationName);

    List<Station> findByStationNameContaining(String stationName);
}
