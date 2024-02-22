package backend.sumnail.domain.nail_shop.repository;

import static backend.sumnail.domain.hashtag.entity.QHashtag.hashtag;
import static backend.sumnail.domain.nail_shop.entity.QNailShop.nailShop;
import static backend.sumnail.domain.station.entity.QStation.station;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.service.port.NailShopRepository;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NailShopRepositoryImpl implements NailShopRepository {
    private final NailShopJpaRepository nailShopJpaRepository;
    private final JPAQueryFactory query;

    @Override
    public Page<NailShop> findAll(Pageable pageable) {
        return nailShopJpaRepository.findAll(pageable);
    }

    @Override
    public NailShop getById(long nailShopId) {
        return nailShopJpaRepository.findById(nailShopId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_NAIL_SHOP));
    }

    @Override
    public List<NailShop> findByHashtagAndStation(String stationName, List<String> hashtags) {
        return query.selectFrom(nailShop)
                .leftJoin(nailShop.hashtags)
                .leftJoin(nailShop.stations)
                .where(
                        allEq(stationName, hashtags)
                )
                .fetch();
    }

    private BooleanExpression stationEq(String stationNameCond) {
        return stationNameCond.isEmpty() ? null : station.stationName.eq(stationNameCond);
    }

    private BooleanExpression ageEq(List<String> hashtags) {
        return hashtags.isEmpty() ? null : hashtag.hashtagName.in(hashtags);
    }

    private BooleanBuilder allEq(String stationNameCond, List<String> hashtags) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(stationEq(stationNameCond));
        builder.and(ageEq(hashtags));
        return builder;
    }

}
