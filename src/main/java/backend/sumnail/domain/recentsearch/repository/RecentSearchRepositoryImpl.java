package backend.sumnail.domain.recentsearch.repository;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecentSearchRepositoryImpl implements RecentSearchRepository{
    private final RecentSearchJpaRepository recentSearchJpaRepository;

    @Override
    public void deleteByUserId(long userId) {
        recentSearchJpaRepository.deleteByUserId(userId);
    }

    @Override
    public List<RecentSearch> findByUserId(long userId){
        return recentSearchJpaRepository.findByUserId(userId);
    }
}
