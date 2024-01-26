package backend.sumnail.domain.hashtag.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HashtagRepositoryImpl implements HashtagRepository {

    private final HashtagJpaRepository hashtagJpaRepository;

    @Override
    public Optional<Hashtag> findByHashtagName(String hashtagName) {
        return hashtagJpaRepository.findByHashtagName(hashtagName);
    }

    @Override
    public List<Hashtag> findAll() {
        return hashtagJpaRepository.findAll();
    }

    @Override
    public Hashtag getById(Long id) {
        return hashtagJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_HASHTAG));
    }
}
