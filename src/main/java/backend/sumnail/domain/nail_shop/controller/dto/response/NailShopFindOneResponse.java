package backend.sumnail.domain.nail_shop.controller.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

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
}
