package backend.sumnail.domain.hashtag.service.port;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import java.util.List;
import java.util.Optional;

public interface HashtagRepository {
    Optional<Hashtag> findByHashtagName(String hashtagName);

    List<Hashtag> findAll();

    Hashtag getById(Long id);

}
