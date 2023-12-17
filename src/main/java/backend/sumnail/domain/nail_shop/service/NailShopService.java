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
import java.util.ArrayList;
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
        if (stationName.isEmpty()) {
            stationName = "";
        }
        List<NailShop> nailShops = nailShopRepository.findNailShopsByHashtagAndStation(stationName, hashtagName);
        for (NailShop nailShop : nailShops) {
            List<String> hashtags = new ArrayList<>();
            for (NailShopHashtag nailShopHashtag : nailShop.getHashtags()) {
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

    public NailShopFindOneResponse findNailShopById(Long id) {

        NailShop nailShop = nailShopRepository.getById(id);

        List<String> hashtags = new ArrayList<>();
        for (NailShopHashtag nailShopHashtag : nailShop.getHashtags()) {
            hashtags.add(nailShopHashtag.getHashtag().getHashtagName());
        }

        NailShopFindOneResponse nailShopFindOneResponse = NailShopFindOneResponse.builder()
                .nailShopId(nailShop.getId())
                .nailShopName(nailShop.getName())
                .detailImages(nailShop.getDetailImages())
                .location(nailShop.getLocation())
                .hashtags(hashtags)
                .businessHour(nailShop.getBusinessHour())
                .employeeNum(nailShop.getEmployeeNum())
                .minimumPrice(nailShop.getMinimumPrice())
                .maximumPrice(nailShop.getMaximumPrice())
//                .mapLng(nailShop.getCoordinate().getX())
//                .mapLat(nailShop.getCoordinate().getY())
                .mapLat(nailShop.getMapLat())
                .mapLng(nailShop.getMapLng())
                .streetAddress(nailShop.getStreetAddress())
                .naverMapLink(nailShop.getNaverMapLink())
                .reservationLink(nailShop.getReservationTable())
                .monthlyNailLink(nailShop.getMonthlyNailLink())
                .build();

        return nailShopFindOneResponse;
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
