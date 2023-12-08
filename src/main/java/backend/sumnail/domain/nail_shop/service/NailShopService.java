package backend.sumnail.domain.nail_shop.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.service.HashtagService;
import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindSavedDto;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;
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

    private final HashtagService hashtagService;

    public NailShopFindSavedDto findSavedNailShop(UserNailShop userNailShop) {
        NailShop nailShop = nailShopRepository.getById(userNailShop.getNailShop().getId());
        List<Hashtag> hashtags = hashtagService.findHashtags(nailShop);
        return NailShopFindSavedDto.of(nailShop, hashtags);
    }

    public NailShop getById(long nailShopId) {
        return nailShopRepository.getById(nailShopId);
    }
}
