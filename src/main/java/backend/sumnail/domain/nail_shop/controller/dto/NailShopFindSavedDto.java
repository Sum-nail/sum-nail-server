package backend.sumnail.domain.nail_shop.controller.dto;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NailShopFindSavedDto {
    private final NailShop nailShop;
    private final List<Hashtag> hashtags;

    public static NailShopFindSavedDto of(NailShop nailShop, List<Hashtag> hashtags){
        return NailShopFindSavedDto.builder()
                .nailShop(nailShop)
                .hashtags(hashtags)
                .build();
    }


}
