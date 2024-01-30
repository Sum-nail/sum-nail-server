package backend.sumnail.domain.nail_shop_hashtag.service.port;

import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import java.util.List;

public interface NailShopHashtagRepository {
    List<NailShopHashtag> getByNailShopId(Long id);

    void save(NailShopHashtag nailShopHashtag);
}
