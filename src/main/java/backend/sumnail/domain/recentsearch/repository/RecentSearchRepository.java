package backend.sumnail.domain.recentsearch.repository;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import java.util.List;

public interface RecentSearchRepository {
    void deleteByUserId(long userId);

    List<RecentSearch> findByUserId(long userId);
}
