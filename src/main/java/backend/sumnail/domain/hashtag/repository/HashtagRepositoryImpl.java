package backend.sumnail.domain.hashtag.repository;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HashtagRepositoryImpl implements HashtagRepository{
    private final HashtagJpaRepository hashtagJpaRepository;

    @Override
    public Hashtag getById(Long id) {
        return hashtagJpaRepository.findById(id)
                // TODO 커스텀 에러 만든 후 수정
                .orElseThrow(()->new RuntimeException("존재하지 않는 해시태그 id 입니다."));
    }
}
