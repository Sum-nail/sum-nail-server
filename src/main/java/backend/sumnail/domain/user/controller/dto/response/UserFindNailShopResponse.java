package backend.sumnail.domain.user.controller.dto.response;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindSavedDto;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserFindNailShopResponse {
    private final Long nailShopId;
    private final String nailShopName;
    private final String location;
    private final String titleImage;
    private final List<String> hashTags;

    public static UserFindNailShopResponse from(NailShopFindSavedDto nailShopFindSavedDto) {
        NailShop nailShop = nailShopFindSavedDto.getNailShop();
        List<String> hashTagNames = nailShopFindSavedDto.getHashtags()
                .stream()
                .map(Hashtag::getHashtagName)
                .toList();

        return UserFindNailShopResponse.builder()
                .nailShopId(nailShop.getId())
                .nailShopName(nailShop.getName())
                .location(nailShop.getLocation())
                .titleImage(nailShop.getTitleImage())
                .hashTags(hashTagNames)
                .build();

    }
}
