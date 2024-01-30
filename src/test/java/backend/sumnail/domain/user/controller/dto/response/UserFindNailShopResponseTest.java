package backend.sumnail.domain.user.controller.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindSavedDto;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserFindNailShopResponseTest {

    @Test
    @DisplayName("NailShopFindSavedDto 객체로 응답을 생성할 수 있다.")
    public void fromTest() {
        //given
        NailShop nailShop = NailShop.builder()
                .id(1L)
                .location("서울시 중구")
                .name("썸네일네일샵")
                .titleImage("http://zoom.us?g=1")
                .build();

        List<Hashtag> hashtags = new ArrayList<>();
        Hashtag hashtag = Hashtag.builder()
                .id(1L)
                .hashtagName("심플한")
                .build();
        hashtags.add(hashtag);

        NailShopFindSavedDto nailShopFindSavedDto = NailShopFindSavedDto.builder()
                .nailShop(nailShop)
                .hashtags(hashtags)
                .build();

        //when
        UserFindNailShopResponse userFindNailShopResponse = UserFindNailShopResponse.from(nailShopFindSavedDto);

        //then
        assertThat(userFindNailShopResponse.getNailShopId()).isEqualTo(1L);
        assertThat(userFindNailShopResponse.getNailShopName()).isEqualTo("썸네일네일샵");
        assertThat(userFindNailShopResponse.getLocation()).isEqualTo("서울시 중구");
        assertThat(userFindNailShopResponse.getTitleImage()).isEqualTo("http://zoom.us?g=1");
        assertThat(userFindNailShopResponse.getHashtags().get(0)).isEqualTo("심플한");
    }


}