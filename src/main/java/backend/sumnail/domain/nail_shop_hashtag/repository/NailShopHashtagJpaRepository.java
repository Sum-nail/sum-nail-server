package backend.sumnail.domain.nail_shop_hashtag.repository;

import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NailShopHashtagJpaRepository extends JpaRepository<NailShopHashtag, Long> {
    List<NailShopHashtag> findByNailShopId(Long id);

    NailShopHashtag save(NailShopHashtag nailShopHashtag);
}
