package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.station.entity.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NailShopRepositoryImpl implements NailShopRepository{
    private final NailShopJpaRepository nailShopJpaRepository;
    @Override
    public List<NailShop> findAll() {
        return nailShopJpaRepository.findAll();
    }

    @Override
    public List<NailShop> findNailShopsByHashtagAndStation(String stationName, String hashtagName){
        return nailShopJpaRepository.findNailShopsByHashtagAndStation(stationName,hashtagName);
    }

    @Override
    public Optional<NailShop> findById(Long id){
        return nailShopJpaRepository.findById(id);
    }
}