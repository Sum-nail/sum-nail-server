package backend.sumnail.domain.recentsearch.repository;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.recentsearch.service.port.RecentSearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public void save(RecentSearch recentSearch) {
        recentSearchJpaRepository.save(recentSearch);
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
