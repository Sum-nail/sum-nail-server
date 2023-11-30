package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.station.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NailShopJpaRepository extends JpaRepository<NailShop,Long> {
    List<NailShop> findAll();

    List<NailShop> findAllByStationsContainingAndHashtagsContaining(Station station, Hashtag hashtag);
}
