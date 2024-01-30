package backend.sumnail.domain.recentsearch.service;

import backend.sumnail.domain.common.service.port.ClockHolder;
import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.recentsearch.service.port.RecentSearchRepository;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user.service.port.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecentSearchService {
    private final RecentSearchRepository recentSearchRepository;
    private final UserRepository userRepository;


    public void deleteAll(long userId) {
        recentSearchRepository.deleteByUserId(userId);
    }

    public List<RecentSearch> findByUserId(long userId) {
        return recentSearchRepository.findByUserId(userId);
    }


    public void addRecentSearch(long userId, String station, ClockHolder clockHolder) {
        if (station.isEmpty()) {
            return;
        }

        if (!recentSearchRepository.findByStation(station).isEmpty()) {
            recentSearchRepository.deleteByStation(station);
        }
        User user = userRepository.getById(userId);
        RecentSearch recentSearch = RecentSearch.createRecentSearch(user, station, clockHolder);
        recentSearchRepository.save(recentSearch);
    }
}
