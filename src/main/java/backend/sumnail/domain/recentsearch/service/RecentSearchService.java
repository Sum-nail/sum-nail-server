package backend.sumnail.domain.recentsearch.service;

import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.recentsearch.repository.RecentSearchRepository;
import java.util.List;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user.repository.UserRepository;
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

    public void addRecentSearch(long userId, String station) {
        if(!recentSearchRepository.findByStation(station).isEmpty()){
            recentSearchRepository.deleteByStation(station);
        }
        User user=userRepository.getById(userId);
        RecentSearch recentSearch = RecentSearch.createRecentSearch(user,station);
        recentSearchRepository.save(recentSearch);
    }
}
