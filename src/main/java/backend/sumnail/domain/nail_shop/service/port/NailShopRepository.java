package backend.sumnail.domain.nail_shop.service.port;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.List;
import java.util.Optional;


public interface NailShopRepository {
    List<NailShop> findAll();

    List<NailShop> findByHashtagAndStation(String stationName, List<String> hashtagName);

    Optional<NailShop> findById(Long id);

    NailShop getById(long id);

    List<NailShop> findByHashtag(List<String> hashtags);

    List<NailShop> findByStation(String stationName);
}
