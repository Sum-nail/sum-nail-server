package backend.sumnail.medium;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//중형 테스트
@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/nail_shop-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
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
    @DisplayName("findAllHashtags로 해시태그 전체 조회가 가능하다")
    void findAllHashtag() {
        //given
        String HashtagName = hashtagRepository.getById(1L).getHashtagName();

        //when
        HashtagFindAllResponse hashtagFindAllResponse = hashtagService.findAllHashtag();
        List<String> hashtagList = hashtagFindAllResponse.getHashtags();

        //then
        assertThat(hashtagList).contains(HashtagName);
    }

    @Test
    @DisplayName("해당 네일샵이 가지고 있는 해시태그 조회가 가능하다")
    @Transactional
    void findHashtags() {
        //given
        NailShop nailShop = nailShopRepository.getById(1L);

        List<Hashtag> originHashtags = nailShop.getHashtags().stream().map(NailShopHashtag::getHashtag).toList();

        //when
        List<Hashtag> hashtags = hashtagService.findHashtags(nailShop);

        //then
        assertThat(hashtags).isEqualTo(originHashtags);
    }

    @Test
    @DisplayName("해시태그가 3개 이상일 때, 해시태그 조회에 실패한다.")
    @Transactional
    void findHashtagsExceedMaxHashtagCount() {
        //given
        NailShop nailShop = nailShopRepository.getById(1L);
        for (Long i = 1L; i <= 3L; i++) {
            nailShopHashtagRepository.save(NailShopHashtag.builder().nailShop(nailShop).hashtag(hashtagRepository.getById(i)).build());
        }

        //when
        Throwable throwable = catchThrowable(() -> hashtagService.findHashtags(nailShop));

        //then
        assertThatThrownBy(() -> {
            hashtagService.findHashtags(nailShop);
        }).isInstanceOf(CustomException.class)
                .hasMessage("해시태그의 최대 갯수를 초과합니다.");
    }
}