package backend.sumnail.domain.hashtag.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;

import java.util.Optional;

public interface HashtagRepository {
    Optional<Hashtag> findByHashtagName(String hashtagName);

}
