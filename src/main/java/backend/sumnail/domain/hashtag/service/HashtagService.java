package backend.sumnail.domain.hashtag.service;

import backend.sumnail.domain.hashtag.controller.dto.response.HashtagFindAllResponse;
import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.repository.NailShopHashtagRepository;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class HashtagService {

    private final HashtagRepository hashtagRepository;
    private final NailShopHashtagRepository nailShopHashtagRepository;

    public HashtagFindAllResponse findAllHashtag() {
        List<Hashtag> hashtags = hashtagRepository.findAll();
        return HashtagFindAllResponse.from(hashtags);
    }

    public List<Hashtag> findHashtags(final NailShop nailShop) {
        List<Hashtag> hashtags = nailShopHashtagRepository.getByNailShopId(nailShop.getId())
                .stream()
                .map(nailShopHashtag -> hashtagRepository.getById(nailShopHashtag.getHashtag().getId()))
                .toList();

        if (hashtags.size() > Hashtag.MAX_HASHTAG_COUNT) {
            throw new CustomException(ErrorCode.EXCEEDED_MAX_HASHTAG);
        }

        return hashtags;
    }
}
