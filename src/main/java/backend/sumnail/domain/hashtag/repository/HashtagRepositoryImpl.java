package backend.sumnail.domain.hashtag.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HashtagRepositoryImpl implements HashtagRepository{
    private final HashtagJpaRepository hashtagJpaRepository;
}
