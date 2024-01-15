package backend.sumnail.mock;

import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.domain.station.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeStationRepository implements StationRepository {
    private final Long id=new AtomicLong().getAndIncrement();
    private final List<Station> data=new ArrayList<>();
    @Override
    public Optional<Station> findByStationName(String stationName) {
        return data.stream()
                .filter(item->item.getStationName().equals(stationName))
                .findAny();
    }

    @Override
    public List<Station> findByStationNameContaining(String stationName) {
        return data.stream()
                .filter(item->item.getStationName().contains(stationName))
                .toList();
    }

    public Station save(Station station){
        if(station.getId()==0||station.getId()==null)
        {
            Station newStation=Station.builder()
                    .id(id)
                    .stationName(station.getStationName())
                    .build();
        }
        else{
            data.removeIf(it->it.getId()==station.getId());
        }
        data.add(station);
        return station;
    }
}
