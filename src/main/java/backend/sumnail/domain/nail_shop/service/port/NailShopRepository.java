package backend.sumnail.domain.nail_shop.service.port;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NailShopRepository {

    List<NailShop> findByHashtagAndStation(String stationName, List<String> hashtagName);

    Page<NailShop> findAll(Pageable pageable);

    NailShop getById(long id);

}
