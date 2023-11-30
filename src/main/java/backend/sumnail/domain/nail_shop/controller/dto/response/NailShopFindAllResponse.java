package backend.sumnail.domain.nail_shop.controller.dto.response;

import lombok.Builder;
import lombok.Getter;


import java.util.List;

@Builder
@Getter
public class NailShopFindAllResponse {
    private Long nailShopId;
    private String nailShopName;
    private String location;
    private List<String> hashtags;
    private String titleImage;

}
