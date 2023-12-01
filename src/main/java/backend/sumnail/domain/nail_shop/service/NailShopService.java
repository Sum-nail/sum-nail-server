package backend.sumnail.domain.nail_shop.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindSavedDto;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;
import backend.sumnail.domain.nail_shop_hashtag.repository.NailShopHashtagRepository;
import backend.sumnail.domain.nail_shop_hashtag.service.NailShopHashtagService;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NailShopService {
    private final NailShopRepository nailShopRepository;

    private final NailShopHashtagService nailShopHashtagService;

    public NailShopFindSavedDto findSavedNailShop(UserNailShop userNailShop){
        NailShop nailShop = nailShopRepository.getById(userNailShop.getId());
        List<Hashtag> hashtags = nailShopHashtagService.findHashtags(nailShop);
        return NailShopFindSavedDto.of(nailShop,hashtags);
    }

    public NailShop getById(long nailShopId){
        return nailShopRepository.getById(nailShopId);
    }
}
