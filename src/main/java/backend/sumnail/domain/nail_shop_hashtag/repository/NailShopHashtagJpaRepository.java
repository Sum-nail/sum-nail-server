package backend.sumnail.domain.nail_shop_hashtag.repository;

import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NailShopHashtagJpaRepository extends JpaRepository<NailShopHashtag, Long> {
    List<NailShopHashtag> findByNailShopId(Long id);

}
