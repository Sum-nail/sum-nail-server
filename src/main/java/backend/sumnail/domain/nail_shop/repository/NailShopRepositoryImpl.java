package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
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
    public Optional<NailShop> findById(Long id) {
        return nailShopJpaRepository.findById(id);
    }

    @Override
    public NailShop getById(long nailShopId) {
        return nailShopJpaRepository.findById(nailShopId)
                // TODO 커스텀 에러 만든 후 수정
                .orElseThrow(() -> new RuntimeException("존재하지 않는 네일샵입니다."));
    }
}
