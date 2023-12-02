package backend.sumnail.domain.hashtag.service;

import backend.sumnail.domain.hashtag.controller.dto.response.HashtagFindAllResponse;
import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public HashtagFindAllResponse findAllHashtag(){
        List<String> list=new ArrayList<>();
        List<Hashtag> hashtags=hashtagRepository.findAll();

        for (Hashtag hashtag:hashtags
             ) {
            list.add(hashtag.getHashtagName());
        }
        HashtagFindAllResponse response=HashtagFindAllResponse.builder()
                .hashtags(list)
                .build();
        return response;
    }
}
