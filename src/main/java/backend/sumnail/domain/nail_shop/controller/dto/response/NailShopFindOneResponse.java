package backend.sumnail.domain.nail_shop.controller.dto.response;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class NailShopFindOneResponse {
    private Long nailShopId;
    private String nailShopName;
    private List<String> detailImages;
    private String location;
    private List<String> hashtags;
    private String businessHour;
    private Long employeeNum;
    private Long minimumPrice;
    private Long maximumPrice;
    private Double mapLat;
    private Double mapLng;
    private String streetAddress;
    private String naverMapLink;
    private String reservationLink;
    private String monthlyNailLink;

    public static NailShopFindOneResponse from(NailShop nailShop){
        List<String> hashtags = nailShop.getHashtags()
                .stream()
                .map(nailShopHashtag -> nailShopHashtag.getHashtag().getHashtagName())
                .toList();
        return NailShopFindOneResponse.builder()
                .nailShopId(nailShop.getId())
                .nailShopName(nailShop.getName())
                .detailImages(nailShop.getDetailImages())
                .location(nailShop.getLocation())
                .hashtags(hashtags)
                .businessHour(nailShop.getBusinessHour())
                .employeeNum(nailShop.getEmployeeNum())
                .minimumPrice(nailShop.getMinimumPrice())
                .maximumPrice(nailShop.getMaximumPrice())
                .mapLat(nailShop.getMapLat())
                .mapLng(nailShop.getMapLng())
                .streetAddress(nailShop.getStreetAddress())
                .naverMapLink(nailShop.getNaverMapLink())
                .reservationLink(nailShop.getReservationTable())
                .monthlyNailLink(nailShop.getMonthlyNailLink())
                .build();
    }
}
