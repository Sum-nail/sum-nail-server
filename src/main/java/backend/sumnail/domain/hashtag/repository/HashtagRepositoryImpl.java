package backend.sumnail.domain.hashtag.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HashtagRepositoryImpl implements HashtagRepository{

    private final HashtagJpaRepository hashtagJpaRepository;

    @Override
    public Optional<Hashtag> findByHashtagName(String hashtagName) {
        return hashtagJpaRepository.findByHashtagName(hashtagName);
    }
}
