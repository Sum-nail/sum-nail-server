package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NailShopRepositoryImpl implements NailShopRepository {
    private NailShopJpaRepository nailShopJpaRepository;

    @Override
    public NailShop getById(Long nailShopId) {
        return nailShopJpaRepository.findById(nailShopId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 네일샵입니다."));
    }
}
