package backend.sumnail.domain.nail_shop.service.port;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NailShopRepository {
    List<NailShop> findAll();

    Page<NailShop> findAll(Pageable pageable);

    List<NailShop> findNailShopsByHashtagAndStation(String stationName, String hashtagName);

    Optional<NailShop> findById(Long id);

    NailShop getById(long id);

    List<NailShop> findNailShopByHashtag(String hashtagName);

    List<NailShop> findNailShopByStation(String stationName);
}
