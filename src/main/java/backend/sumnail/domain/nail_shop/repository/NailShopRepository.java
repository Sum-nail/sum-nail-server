package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.station.entity.Station;

import java.util.List;
import java.util.Optional;


public interface NailShopRepository{
    List<NailShop> findAll();

    List<NailShop> findNailShopsByHashtagAndStation(String stationName, String hashtagName);

    Optional<NailShop> findById(Long id);
}