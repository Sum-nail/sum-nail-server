package backend.sumnail.domain.nail_shop.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindAllResponse;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.station.entity.Station;
import backend.sumnail.domain.station.repository.StationJpaRepository;
import backend.sumnail.domain.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NailShopService {

    private final NailShopRepository nailShopRepository;
    private final StationRepository stationRepository;

    private final HashtagRepository hashtagRepository;

    public List<NailShopFindAllResponse> findAllShop() {
        List<NailShopFindAllResponse> response = new ArrayList<>();
        List<NailShop> nailShops = nailShopRepository.findAll();
        for (NailShop nailShop : nailShops
        ) {
            List<String> hashtags = new ArrayList<>();
            for (NailShopHashtag nailShopHashtag : nailShop.getHashtags()
            ) {
                hashtags.add(nailShopHashtag.getHashtag().getHashtagName());
            }
            NailShopFindAllResponse nailShopFindAllResponse = NailShopFindAllResponse.builder()
                    .nailShopId(nailShop.getId())
                    .nailShopName(nailShop.getName())
                    .location(nailShop.getLocation())
                    .titleImage(nailShop.getTitleImage())
                    .hashtags(hashtags)
                    .build();
            response.add(nailShopFindAllResponse);
        }
        return response;
    }

    public List<NailShopFindAllResponse> searchNailShop(String stationName, String hashtagName) {
        List<NailShopFindAllResponse> response = new ArrayList<>();
        if(stationName.isEmpty()){
            stationName="";
        }
        List<NailShop> nailShops= nailShopRepository.findNailShopsByHashtagAndStation(stationName,hashtagName);
        for (NailShop nailShop : nailShops
        ) {
            List<String> hashtags = new ArrayList<>();
            for (NailShopHashtag nailShopHashtag : nailShop.getHashtags()
            ) {
                hashtags.add(nailShopHashtag.getHashtag().getHashtagName());
            }
            NailShopFindAllResponse nailShopFindAllResponse = NailShopFindAllResponse.builder()
                    .nailShopId(nailShop.getId())
                    .nailShopName(nailShop.getName())
                    .location(nailShop.getLocation())
                    .titleImage(nailShop.getTitleImage())
                    .hashtags(hashtags)
                    .build();
            response.add(nailShopFindAllResponse);
        }
        return response;
    }
}
