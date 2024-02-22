package backend.sumnail.domain.nail_shop.controller.dto;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NailShopFindDto {
    private Long nailShopId;
    private String nailShopName;
    private String location;
    private List<String> hashtags;
    private String titleImage;

    public static NailShopFindDto from(NailShop nailShop, List<String> hashtags) {
        return NailShopFindDto.builder()
                .nailShopId(nailShop.getId())
                .nailShopName(nailShop.getName())
                .location(nailShop.getLocation())
                .titleImage(nailShop.getTitleImage())
                .hashtags(hashtags)
                .build();
    }
}
