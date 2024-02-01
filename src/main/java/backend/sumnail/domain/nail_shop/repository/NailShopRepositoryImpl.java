package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.hashtag.entity.QHashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.service.port.NailShopRepository;
import backend.sumnail.domain.station.entity.QStation;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import java.util.List;
import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static backend.sumnail.domain.hashtag.entity.QHashtag.hashtag;
import static backend.sumnail.domain.nail_shop.entity.QNailShop.nailShop;
import static backend.sumnail.domain.station.entity.QStation.station;

@Repository
@RequiredArgsConstructor
public class NailShopRepositoryImpl implements NailShopRepository {
    private final NailShopJpaRepository nailShopJpaRepository;
    private final JPAQueryFactory query;

    @Override
    public List<NailShop> findAll() {
        return nailShopJpaRepository.findAll();
    }

    @Override
    public List<NailShop> findByHashtagAndStation(String stationName, List<String> hashtags) {
        return query.selectFrom(nailShop)
                .leftJoin(nailShop.hashtags)
                .leftJoin(nailShop.stations)
                .where(
                        hashtag.hashtagName.in(hashtags)
                                .and(station.stationName.eq(stationName))
                )
                .fetch();
    }

    @Override
    public Optional<NailShop> findById(Long id) {
        return nailShopJpaRepository.findById(id);
    }

    @Override
    public NailShop getById(long nailShopId) {
        return nailShopJpaRepository.findById(nailShopId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_NAIL_SHOP));
    }

    @Override
    public List<NailShop> findByHashtag(List<String> hashtags) {
        return query.selectFrom(nailShop)
                .leftJoin(nailShop.hashtags)
                .where(
                        hashtag.hashtagName.in(hashtags)
                )
                .fetch();
    }

    @Override
    public List<NailShop> findByStation(String stationName) {
        return query.selectFrom(nailShop)
                .leftJoin(nailShop.stations)
                .where(
                station.stationName.eq(stationName)
                )
                .fetch();
    }
}
