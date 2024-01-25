package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NailShopJpaRepository extends JpaRepository<NailShop, Long> {

    @Query("SELECT DISTINCT ns FROM NailShop ns " +
            "LEFT JOIN ns.hashtags nh " +
            "LEFT JOIN ns.stations s " +
            "WHERE (COALESCE(:hashtagName, '') = '' OR nh.hashtag.hashtagName = :hashtagName) " +
            "AND (COALESCE(:stationName, '') = '' OR s.station.stationName = :stationName)")
    List<NailShop> findNailShopsByHashtagAndStation(
            String stationName,
            String hashtagName
    );

    @Query("SELECT DISTINCT ns FROM NailShop ns " +
            "LEFT JOIN ns.hashtags nh " +
            "WHERE (COALESCE(:hashtagName, '') = '' OR nh.hashtag.hashtagName = :hashtagName)")
    List<NailShop> findNailShopByHashtag(String hashtagName);

    @Query("SELECT DISTINCT ns FROM NailShop ns " +
            "LEFT JOIN ns.stations s " +
            "WHERE (COALESCE(:stationName, '') = '' OR s.station.stationName = :stationName)")
    List<NailShop> findNailShopByStation(String stationName);

    Optional<NailShop> findById(Long id);
}
