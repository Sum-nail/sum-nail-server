package backend.sumnail.domain.nail_shop.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindAllResponse;
import backend.sumnail.domain.nail_shop.controller.dto.response.NailShopFindOneResponse;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.mock.FakeHashtagRepository;
import backend.sumnail.mock.FakeNailShopHashtagRepository;
import backend.sumnail.mock.FakeNailShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NailShopServiceTest {

    private NailShopService nailShopService;
    @BeforeEach
    void init(){
        FakeNailShopRepository fakeNailShopRepository=new FakeNailShopRepository();
        FakeHashtagRepository fakeHashtagRepository=new FakeHashtagRepository();
        FakeNailShopHashtagRepository fakeNailShopHashtagRepository=new FakeNailShopHashtagRepository();
        this.nailShopService=NailShopService.builder()
                .nailShopRepository(fakeNailShopRepository)
                .build();

        Hashtag hashtag=Hashtag.builder()
                .id(1L)
                .hashtagName("귀여운")
                .build();
        NailShop nailShop=NailShop.builder()
                .id(1L)
                .name("네일샵1")
                .location("서울시 강남구")
                .titleImage("이미지")
                .hashtags(List.of(NailShopHashtag.builder()
                        .hashtag(hashtag)
                        .build()))
                .build();
        NailShopHashtag nailShopHashtag=NailShopHashtag.builder()
                .nailShop(nailShop)
                .hashtag(hashtag)
                .build();

        fakeNailShopRepository.save(nailShop);
        fakeHashtagRepository.save(hashtag);
        fakeNailShopHashtagRepository.save(nailShopHashtag);
    }

    @Test
    @DisplayName("findAllShop로 모든 네일샵을 조회할 수 있다.")
    void findAllShop() {
        //given
        String hashtagName="귀여운";
        Long nailShopId=1L;
        String nailShopName="네일샵1";

        //when
        List<NailShopFindAllResponse> nailShopFindAllResponses=nailShopService.findAllShop();

        //then
        assertThat(nailShopFindAllResponses.size()).isEqualTo(1);
        assertThat(nailShopFindAllResponses.get(0).getNailShopId()).isEqualTo(nailShopId);
        assertThat(nailShopFindAllResponses.get(0).getHashtags().get(0)).isEqualTo(hashtagName);
        assertThat(nailShopFindAllResponses.get(0).getNailShopName()).isEqualTo(nailShopName);
    }

    @Test
    @DisplayName("searchNailShop는 역과 해시태그를 포함하는 네일샵을 조회할 수 있다.")
    void searchNailShop() {
        //given
        Long nailShopId=1L;
        String hashtagName="귀여운";
        String nailShopName="네일샵1";

        //when
        List<NailShopFindAllResponse> nailShopFindAllResponses=nailShopService.searchNailShop("",hashtagName);

        //then
        assertThat(nailShopFindAllResponses.get(0).getNailShopId()).isEqualTo(nailShopId);
        assertThat(nailShopFindAllResponses.get(0).getHashtags().get(0)).isEqualTo(hashtagName);
        assertThat(nailShopFindAllResponses.get(0).getNailShopName()).isEqualTo(nailShopName);
    }

    @Test
    @DisplayName("findNailShopById는 id로 네일샵이 포함된 DTO를 조회할 수 있다.")
    void findNailShopById() {
        //given
        Long nailShopId=1L;
        String hashtagName="귀여운";
        String nailShopName="네일샵1";

        //when
        NailShopFindOneResponse nailShopFindOneResponse=nailShopService.findNailShopById(nailShopId);

        //then
        assertThat(nailShopFindOneResponse.getNailShopId()).isEqualTo(nailShopId);
        assertThat(nailShopFindOneResponse.getHashtags().get(0)).isEqualTo(hashtagName);
        assertThat(nailShopFindOneResponse.getNailShopName()).isEqualTo(nailShopName);
    }

    @Test
    @DisplayName("findSavedNailShop는 유저가 저장한 네일샵을 조회할 수 있다.")
    void findSavedNailShop() {
    }

    @Test
    @DisplayName("getById는 id로 네일샵을 조회할 수 있다.")
    void getById() {
        //given
        Long nailShopId=1L;
        String hashtagName="귀여운";
        String nailShopName="네일샵1";

        //when
        NailShop nailshop=nailShopService.getById(nailShopId);

        //then
        assertThat(nailshop.getId()).isEqualTo(nailShopId);
        assertThat(nailshop.getHashtags().get(0).getHashtag().getHashtagName()).isEqualTo(hashtagName);
        assertThat(nailshop.getName()).isEqualTo(nailShopName);
    }
}