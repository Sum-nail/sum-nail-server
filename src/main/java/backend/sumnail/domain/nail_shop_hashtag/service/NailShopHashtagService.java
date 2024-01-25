package backend.sumnail.domain.nail_shop_hashtag.service;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.nail_shop_hashtag.repository.NailShopHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NailShopHashtagService {
    private final NailShopHashtagRepository nailShopHashtagRepository;

    public List<NailShopHashtag> getByNailShopId(NailShop nailShop) {
        return nailShopHashtagRepository.getByNailShopId(nailShop.getId());
    }
}
