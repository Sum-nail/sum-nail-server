package backend.sumnail.domain.nail_shop.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

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
    private Float mapLat;
    private Float mapLng;
    private String streetAddress;
    private String naverMapLink;
    private String reservationLink;
    private String monthlyNailLink;
}
