package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NailShopRepositoryImpl implements NailShopRepository{
    private final NailShopJpaRepository nailShopJpaRepository;
    @Override
    public List<NailShop> findAll() {
        return nailShopJpaRepository.findAll();
    }
}
