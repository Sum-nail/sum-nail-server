package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;

import java.util.List;
import java.util.Optional;


public interface NailShopRepository {
    List<NailShop> findAll();

    List<NailShop> findNailShopsByHashtagAndStation(String stationName, String hashtagName);

    Optional<NailShop> findById(Long id);

    NailShop getById(long id);

    List<NailShop> findNailShopByHashtag(String hashtagName);

    List<NailShop> findNailShopByStation(String stationName);
}
