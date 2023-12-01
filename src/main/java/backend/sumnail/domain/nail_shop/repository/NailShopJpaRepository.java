package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.station.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NailShopJpaRepository extends JpaRepository<NailShop,Long> {
    List<NailShop> findAll();

    @Query("SELECT DISTINCT ns FROM NailShop ns " +
            "LEFT JOIN ns.hashtags nh " +
            "LEFT JOIN ns.stations s " +
            "WHERE (COALESCE(:hashtagName, '') = '' OR nh.hashtag.hashtagName = :hashtagName) " +
            "AND (COALESCE(:stationName, '') = '' OR s.station.stationName = :stationName)")
    List<NailShop> findNailShopsByHashtagAndStation(
             String stationName,
             String hashtagName
    );

    Optional<NailShop> findById(Long id);
}
