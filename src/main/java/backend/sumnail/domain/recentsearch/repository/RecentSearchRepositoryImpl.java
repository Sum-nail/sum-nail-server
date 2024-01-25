package backend.sumnail.domain.recentsearch.repository;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecentSearchRepositoryImpl implements RecentSearchRepository {
    private final RecentSearchJpaRepository recentSearchJpaRepository;

    @Override
    public void deleteByUserId(long userId) {
        recentSearchJpaRepository.deleteByUserId(userId);
    }

    @Override
    public List<RecentSearch> findByUserId(long userId) {
        return recentSearchJpaRepository.findByUserId(userId);
    }

    @Override
    public RecentSearch save(RecentSearch recentSearch) {
        return recentSearchJpaRepository.save(recentSearch);
    }

    @Override
    public void deleteByStation(String station) {
        recentSearchJpaRepository.deleteByStation(station);
    }

    @Override
    public List<RecentSearch> findByStation(String station) {
        return recentSearchJpaRepository.findByStation(station);
    }
}
