package backend.sumnail.domain.nail_shop.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.service.HashtagService;
import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindSavedDto;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindAllResponse;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindOneResponse;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NailShopService {

    private final NailShopRepository nailShopRepository;
    private final HashtagService hashtagService;
    public List<NailShopFindAllResponse> findAllShop() {
        List<NailShop> nailShops = nailShopRepository.findAll();
        List<NailShopFindAllResponse> response = nailShops
                .stream()
                .map(NailShop->NailShopFindAllResponse.from(NailShop))
                .toList();
        return response;
    }

    public List<NailShopFindAllResponse> searchNailShop(String stationName, String hashtagName) {
        if(stationName.isEmpty()){
            stationName="";
        }
        List<NailShop> nailShops= nailShopRepository.findNailShopsByHashtagAndStation(stationName,hashtagName);
        List<NailShopFindAllResponse> response = nailShops
                .stream()
                .map(NailShop->NailShopFindAllResponse.from(NailShop))
                .toList();
        return response;
    }

    public NailShopFindOneResponse findNailShopById(Long id){
        NailShop nailShop=nailShopRepository.getById(id);

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
}
