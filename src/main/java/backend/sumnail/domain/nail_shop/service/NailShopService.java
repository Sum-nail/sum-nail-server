package backend.sumnail.domain.nail_shop.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.service.HashtagService;
import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindDto;
import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindSavedDto;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindAllResponse;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindOneResponse;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.service.port.NailShopRepository;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.nail_shop_hashtag.service.port.NailShopHashtagRepository;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class NailShopService {

    private final NailShopRepository nailShopRepository;
    private final HashtagService hashtagService;
    private final NailShopHashtagRepository nailShopHashtagRepository;

    @Cacheable(cacheNames = "NAILSHOP", cacheManager = "cacheManager")
    public NailShopFindAllResponse findAllShop(Pageable pageable) {
        Page<NailShop> nailShopPage = nailShopRepository.findAll(pageable);
        List<NailShop> nailShops = nailShopPage.getContent();

        return toNailShopFindAllResponse(nailShops);
    }


    public NailShopFindAllResponse searchNailShop(String stationName, String hashtagName) {
        List<NailShop> nailShops;
        if (stationName.isEmpty() && hashtagName.isEmpty()) {
            nailShops = nailShopRepository.findAll();
        } else if (stationName.isEmpty()) {
            nailShops = nailShopRepository.findNailShopByHashtag(hashtagName);
        } else if (hashtagName.isEmpty()) {
            nailShops = nailShopRepository.findNailShopByStation(stationName);
        } else {
            nailShops = nailShopRepository.findNailShopsByHashtagAndStation(stationName, hashtagName);
        }

        return toNailShopFindAllResponse(nailShops);
    }

    public NailShopFindOneResponse findNailShopById(Long id) {
        NailShop nailShop = nailShopRepository.getById(id);

        return NailShopFindOneResponse.from(nailShop);
    }

    public NailShopFindSavedDto findSavedNailShop(UserNailShop userNailShop) {
        NailShop nailShop = nailShopRepository.getById(userNailShop.getNailShop().getId());
        List<Hashtag> hashtags = hashtagService.findHashtags(nailShop);
        return NailShopFindSavedDto.of(nailShop, hashtags);
    }

    public NailShop getById(long nailShopId) {
        return nailShopRepository.getById(nailShopId);
    }

    private NailShopFindAllResponse toNailShopFindAllResponse(List<NailShop> nailShops) {
        List<NailShopFindDto> response = nailShops.stream()
                .map(nailShop -> {
                    List<String> hashtags = nailShopHashtagRepository.getByNailShopId(nailShop.getId()).stream()
                            .map(NailShopHashtag::getHashtag)
                            .map(Hashtag::getHashtagName)
                            .toList();
                    return NailShopFindDto.from(nailShop, hashtags);
                })
                .toList();
        return NailShopFindAllResponse.from(response);
    }
}
