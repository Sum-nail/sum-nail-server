package backend.sumnail.domain.nail_shop_hashtag.repository;

import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NailShopHashtagRepositoryImpl implements NailShopHashtagRepository {
    private final NailShopHashtagJpaRepository nailShopHashTagJpaRepository;

    @Override
    public List<NailShopHashtag> getByNailShopId(Long id) {
        return nailShopHashTagJpaRepository.findByNailShopId(id);
    }

    @Override
    public void save(NailShopHashtag nailShopHashtag) {
        nailShopHashTagJpaRepository.save(nailShopHashtag);
    }
}
