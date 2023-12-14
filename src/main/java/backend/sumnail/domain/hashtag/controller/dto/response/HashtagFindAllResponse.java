package backend.sumnail.domain.hashtag.controller.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HashtagFindAllResponse {

    private List<String> hashtags;
}
