package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NailShopRepositoryImpl implements NailShopRepository {
    private final NailShopJpaRepository nailShopJpaRepository;

    @Override
    public NailShop getById(long nailShopId) {
        return nailShopJpaRepository.findById(nailShopId)
                // TODO 커스텀 에러 만든 후 수정
                .orElseThrow(() -> new RuntimeException("존재하지 않는 네일샵입니다."));
    }
}
