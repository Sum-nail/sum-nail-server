package backend.sumnail.domain.station.repository;

import backend.sumnail.domain.station.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationJpaRepository extends JpaRepository<Station, Long>
{
    Optional<Station> findByStationName(String stationName);
}
