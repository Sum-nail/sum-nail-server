package backend.sumnail.domain.hashtag.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public Hashtag getById(NailShopHashtag nailShopHashtag){
        return hashtagRepository.getById(nailShopHashtag.getHashtag().getId());
    }
}

