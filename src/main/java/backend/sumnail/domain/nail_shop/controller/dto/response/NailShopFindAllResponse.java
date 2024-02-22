package backend.sumnail.domain.nail_shop.controller.dto.response;

import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class NailShopFindAllResponse {
    List<NailShopFindDto> nailShops;

    public static NailShopFindAllResponse from(List<NailShopFindDto> nailShopFindDtos){
        return NailShopFindAllResponse.builder()
                .nailShops(nailShopFindDtos)
                .build();
    }
}
