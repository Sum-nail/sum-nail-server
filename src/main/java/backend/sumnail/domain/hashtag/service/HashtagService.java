package backend.sumnail.domain.hashtag.service;

import backend.sumnail.domain.hashtag.controller.dto.response.HashtagFindAllResponse;
import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.service.port.HashtagRepository;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.service.NailShopHashtagService;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class HashtagService {

    private final HashtagRepository hashtagRepository;
    private final NailShopHashtagService nailShopHashtagService;

    @Cacheable(cacheNames = "HASHTAG", cacheManager = "cacheManager")
    public HashtagFindAllResponse findAllHashtag() {
        List<Hashtag> hashtags = hashtagRepository.findAll();
        return HashtagFindAllResponse.from(hashtags);
    }

    public List<Hashtag> findHashtags(final NailShop nailShop) {
        List<Hashtag> hashtags = nailShopHashtagService.getByNailShopId(nailShop)
                .stream()
                .map(nailShopHashtag -> hashtagRepository.getById(nailShopHashtag.getHashtag().getId()))
                .toList();

        if (hashtags.size() > Hashtag.MAX_HASHTAG_COUNT) {
            throw new CustomException(ErrorCode.EXCEEDED_MAX_HASHTAG);
        }

        return hashtags;
    }
}
