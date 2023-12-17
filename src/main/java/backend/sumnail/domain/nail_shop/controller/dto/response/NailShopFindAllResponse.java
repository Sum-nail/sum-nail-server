package backend.sumnail.domain.nail_shop.controller.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NailShopFindAllResponse {
    private Long nailShopId;
    private String nailShopName;
    private String location;
    private List<String> hashtags;
    private String titleImage;

}
