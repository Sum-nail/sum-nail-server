package backend.sumnail.domain.recentsearch.repository;


import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentSearchJpaRepository extends JpaRepository<RecentSearch, Long> {

    void deleteByUserId(long userId);

    List<RecentSearch> findByUserId(long userId);

    RecentSearch save(RecentSearch recentSearch);

    void deleteByStation(String station);

    List<RecentSearch> findByStation(String station);
}
