package backend.sumnail.domain.hashtag.service;

import backend.sumnail.domain.hashtag.controller.dto.response.HashtagFindAllResponse;
import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.mock.FakeHashtagRepository;
import backend.sumnail.mock.FakeNailShopHashtagRepository;
import backend.sumnail.mock.FakeNailShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class HashtagServiceTest {

    private HashtagService hashtagService;

    private FakeNailShopRepository fakeNailShopRepository=new FakeNailShopRepository();
    FakeNailShopHashtagRepository fakeNailShopHashtagRepository=new FakeNailShopHashtagRepository();
    FakeHashtagRepository fakeHashtagRepository=new FakeHashtagRepository();
    @BeforeEach
    void init(){
        this.hashtagService=HashtagService.builder()
                .hashtagRepository(fakeHashtagRepository)
                .nailShopHashtagRepository(fakeNailShopHashtagRepository)
                .build();
        fakeHashtagRepository.save(Hashtag.builder()
                .id(1L)
                .hashtagName("귀여운")
                .build());
        fakeHashtagRepository.save(Hashtag.builder()
                .id(2L)
                .hashtagName("깜찍한")
                .build());
        fakeHashtagRepository.save(Hashtag.builder()
                .id(3L)
                .hashtagName("멋진")
                .build());
        fakeHashtagRepository.save(Hashtag.builder()
                .id(4L)
                .hashtagName("훈훈한")
                .build());
    }

    @Test
    @DisplayName("findAllHashtags로 해시태그 전체 조회가 가능하다")
    void findAllHashtag() {
        //given
        String HashtagName="귀여운";

        //when
        HashtagFindAllResponse hashtagFindAllResponse = hashtagService.findAllHashtag();
        List<String> hashtagList=hashtagFindAllResponse.getHashtags();

        //then
        assertThat(hashtagList).contains(HashtagName);
    }
    @Test
    @DisplayName("해당 네일샵이 가지고 있는 해시태그 조회가 가능하다")
    @Transactional
    void findHashtags() {
        //given
        NailShop nailShop=NailShop.builder()
                .id(1L)
                .build();
        fakeNailShopRepository.save(nailShop);
        fakeNailShopHashtagRepository.save(NailShopHashtag.builder().nailShop(nailShop).hashtag(fakeHashtagRepository.getById(1L)).build());

        //when
        List<Hashtag> originHashtags=fakeNailShopHashtagRepository.getByNailShopId(nailShop.getId()).stream().map(NailShopHashtag::getHashtag).toList();
        List<Hashtag> hashtags=hashtagService.findHashtags(nailShop);

        //then
        assertThat(hashtags).isEqualTo(originHashtags);
    }
    @Test
    @DisplayName("해시태그가 3개 이상일 때, 해시태그 조회에 실패한다.")
    @Transactional
    void findHashtagsExceedMaxHashtagCount() {
        //given
        NailShop nailShop=NailShop.builder()
                .id(1L)
                .build();
        fakeNailShopRepository.save(nailShop);

        for(Long i=1L;i<=4L;i++){
            fakeNailShopHashtagRepository.save(NailShopHashtag.builder().nailShop(nailShop).hashtag(fakeHashtagRepository.getById(i)).build());
        }

        //when
        Throwable throwable = catchThrowable(() -> hashtagService.findHashtags(nailShop));

        //then
        assertThat(throwable).isInstanceOf(CustomException.class);
    }
}