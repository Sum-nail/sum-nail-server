package backend.sumnail.domain.hashtag.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.service.NailShopHashtagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    private final NailShopHashtagService nailShopHashtagService;

    public List<Hashtag> findHashtags(final NailShop nailShop) {
        List<Hashtag> hashtags = nailShopHashtagService.getByNailShopId(nailShop)
                .stream()
                .map(nailShopHashtag -> hashtagRepository.getById(nailShopHashtag.getHashtag().getId()))
                .toList();

        if (hashtags.size() > Hashtag.MAX_HASHTAG_COUNT) {
            throw new RuntimeException("해시태그의 최대개수는 3개입니다.");
        }

        return hashtags;
    }
}

