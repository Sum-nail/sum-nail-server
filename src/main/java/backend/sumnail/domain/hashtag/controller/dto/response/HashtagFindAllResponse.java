package backend.sumnail.domain.hashtag.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HashtagFindAllResponse {

    private List<String> hashtags;
}
