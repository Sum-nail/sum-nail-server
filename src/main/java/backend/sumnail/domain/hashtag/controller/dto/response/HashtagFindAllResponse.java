package backend.sumnail.domain.hashtag.controller.dto.response;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HashtagFindAllResponse {

    private List<String> hashtags;

    public static HashtagFindAllResponse from(List<Hashtag> hashtags){

        List<String> hashtagNames= hashtags
                .stream()
                .map(Hashtag::getHashtagName)
                .toList();

        return HashtagFindAllResponse.builder()
                .hashtags(hashtagNames)
                .build();
    }
}
