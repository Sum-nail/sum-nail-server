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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class HashtagServiceTest {

    private HashtagService hashtagService;


    @BeforeEach
    void init() {
        FakeNailShopRepository fakeNailShopRepository = new FakeNailShopRepository();
        FakeNailShopHashtagRepository fakeNailShopHashtagRepository = new FakeNailShopHashtagRepository();
        FakeHashtagRepository fakeHashtagRepository = new FakeHashtagRepository();
        this.hashtagService = HashtagService.builder()
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
        NailShop nailShop = NailShop.builder()
                .id(1L)
                .build();
        fakeNailShopRepository.save(nailShop);
        fakeNailShopHashtagRepository.save(NailShopHashtag.builder().nailShop(nailShop).hashtag(fakeHashtagRepository.getById(1L)).build());
        NailShop nailShopOverHashtag = NailShop.builder()
                .id(2L)
                .build();
        fakeNailShopRepository.save(nailShopOverHashtag);
        for (Long i = 1L; i <= 4L; i++) {
            fakeNailShopHashtagRepository.save(NailShopHashtag.builder().nailShop(nailShopOverHashtag).hashtag(fakeHashtagRepository.getById(i)).build());
        }
    }

    @Test
    @DisplayName("findAllHashtags로 해시태그 전체 조회가 가능하다")
    void findAllHashtag() {
        //given
        String HashtagName = "귀여운";
        //when
        HashtagFindAllResponse hashtagFindAllResponse = hashtagService.findAllHashtag();
        //then
        List<String> hashtagList = hashtagFindAllResponse.getHashtags();
        assertThat(hashtagList.get(0)).isEqualTo(HashtagName);
    }

    @Test
    @DisplayName("해당 네일샵이 가지고 있는 해시태그 조회가 가능하다")
    void findHashtags() {
        //given
        String HashtagName = "귀여운";
        NailShop nailShop = NailShop.builder()
                .id(1L)
                .build();
        //when
        List<Hashtag> hashtags = hashtagService.findHashtags(nailShop);
        //then
        assertThat(hashtags.get(0).getHashtagName()).isEqualTo("귀여운");
    }

    @Test
    @DisplayName("해시태그가 3개 이상일 때, 해시태그 조회에 실패한다.")
    void findHashtagsExceedMaxHashtagCount() {
        //given
        NailShop nailShop = NailShop.builder()
                .id(2L)
                .build();
        //when
        //then
        assertThatThrownBy(() -> {
            hashtagService.findHashtags(nailShop);
        }).isInstanceOf(CustomException.class)
                .hasMessage("해시태그의 최대 갯수를 초과합니다.");
    }
}