package backend.sumnail.domain.hashtag.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;

public interface HashtagRepository {
    Hashtag getById(Long id);
}
