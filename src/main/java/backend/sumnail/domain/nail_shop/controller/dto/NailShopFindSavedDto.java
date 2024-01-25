package backend.sumnail.domain.nail_shop.controller.dto;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class NailShopFindSavedDto {
    private final NailShop nailShop;
    private final List<Hashtag> hashtags;

    public static NailShopFindSavedDto of(NailShop nailShop, List<Hashtag> hashtags) {
        return NailShopFindSavedDto.builder()
                .nailShop(nailShop)
                .hashtags(hashtags)
                .build();
    }


}
