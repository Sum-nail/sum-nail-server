package backend.sumnail.domain.nail_shop.controller.dto.response;

import backend.sumnail.domain.nail_shop.entity.NailShop;
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

    public static NailShopFindAllResponse from(NailShop nailShop){
        return NailShopFindAllResponse.builder()
                .nailShopId(nailShop.getId())
                .nailShopName(nailShop.getName())
                .location(nailShop.getLocation())
                .titleImage(nailShop.getTitleImage())
                .hashtags(nailShop.getHashtags().stream().map(hashtag -> hashtag.getHashtag().getHashtagName()).toList())
                .build();
    }
}
