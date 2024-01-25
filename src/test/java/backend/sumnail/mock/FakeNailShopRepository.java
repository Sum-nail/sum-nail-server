package backend.sumnail.mock;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeNailShopRepository implements NailShopRepository {

    private final AtomicLong id = new AtomicLong(0);
    private final List<NailShop> data = new ArrayList<>();

    @Override
    public List<NailShop> findAll() {
        return data;
    }

    @Override
    public List<NailShop> findNailShopsByHashtagAndStation(String stationName, String hashtagName) {
        return data.stream()
                .filter(item -> item.getStations().stream().anyMatch(station -> station.getStation().getStationName().contains(stationName))
                        && item.getHashtags().stream().anyMatch(hashtag -> hashtag.getHashtag().getHashtagName().contains(hashtagName)))
                .toList();
    }

    @Override
    public Optional<NailShop> findById(Long id) {
        return data.stream()
                .filter(item -> item.getId().equals(id))
                .findAny();
    }

    @Override
    public NailShop getById(long id) {
        return data.stream()
                .filter(item -> item.getId().equals(id))
                .findAny()
                .orElseThrow();
    }

    @Override
    public List<NailShop> findNailShopByHashtag(String hashtagName) {
        return data.stream()
                .filter(item -> item.getHashtags().stream().anyMatch(hashtag -> hashtag.getHashtag().getHashtagName().equals(hashtagName)))
                .toList();
    }

    @Override
    public List<NailShop> findNailShopByStation(String stationName) {
        return data.stream()
                .filter(item -> item.getStations().stream().anyMatch(station -> station.getStation().getStationName().equals(stationName)))
                .toList();
    }

    public NailShop save(NailShop nailShop) {
        if (nailShop.getId() == null || nailShop.getId() == 0) {
            NailShop newNailShop = NailShop.builder()
                    .id(id.get())
                    .build();
        } else {
            data.removeIf(it -> it.getId() == nailShop.getId());
        }
        data.add(nailShop);
        return nailShop;
    }
}
