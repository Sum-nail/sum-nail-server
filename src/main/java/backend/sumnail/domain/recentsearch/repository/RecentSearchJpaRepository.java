package backend.sumnail.domain.recentsearch.repository;


import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentSearchJpaRepository extends JpaRepository<RecentSearch, Long> {

    void deleteByUserId(long userId);

    List<RecentSearch> findByUserId(long userId);

    RecentSearch save(RecentSearch recentSearch);

    void deleteByStation(String station);

    List<RecentSearch> findByStation(String station);
}
