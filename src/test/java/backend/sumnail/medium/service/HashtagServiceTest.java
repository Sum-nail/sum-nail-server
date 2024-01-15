package backend.sumnail.medium.service;

import backend.sumnail.domain.hashtag.controller.dto.response.HashtagFindAllResponse;
import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import backend.sumnail.domain.hashtag.service.HashtagService;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;

import backend.sumnail.domain.nail_shop_hashtag.repository.NailShopHashtagRepository;
import backend.sumnail.global.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
//중형 테스트
@SpringBootTest
class HashtagServiceTest {

    @Autowired
    HashtagService hashtagService;
    @Autowired
    HashtagRepository hashtagRepository;
    @Autowired
    NailShopRepository nailShopRepository;
    @Autowired
    NailShopHashtagRepository nailShopHashtagRepository;
    @Test
    @DisplayName("해시태그 전체 조회")
    void findAllHashtag() {
        //given
        String HashtagName=hashtagRepository.getById(1L).getHashtagName();

        //when
        HashtagFindAllResponse hashtagFindAllResponse = hashtagService.findAllHashtag();
        List<String> hashtagList=hashtagFindAllResponse.getHashtags();

        //then
        assertThat(hashtagList).contains(HashtagName);
    }
    @Test
    @DisplayName("해시태그 조회")
    @Transactional
    void findHashtags() {
        //given
        NailShop nailShop=nailShopRepository.getById(1L);

        List<Hashtag> originHashtags=nailShop.getHashtags().stream().map(NailShopHashtag::getHashtag).toList();

        //when
        List<Hashtag> hashtags=hashtagService.findHashtags(nailShop);

        //then
        assertThat(hashtags).isEqualTo(originHashtags);
    }
    @Test
    @DisplayName("해시태그 조회 실패 - 해시태그가 3개 이상일 때")
    @Transactional
    void findHashtagsExceedMaxHashtagCount() {
        //given
        NailShop nailShop=nailShopRepository.getById(1L);
        for(Long i=1L;i<=3L;i++){
            nailShopHashtagRepository.save(NailShopHashtag.builder().nailShop(nailShop).hashtag(hashtagRepository.getById(i)).build());
        }

        //when
        Throwable throwable = catchThrowable(() -> hashtagService.findHashtags(nailShop));

        //then
        assertThat(throwable).isInstanceOf(CustomException.class);
    }
}