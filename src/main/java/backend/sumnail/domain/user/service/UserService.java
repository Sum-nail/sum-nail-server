package backend.sumnail.domain.user.service;

import backend.sumnail.domain.nail_shop.controller.dto.NailShopFindSavedDto;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.service.NailShopService;
import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.recentsearch.service.RecentSearchService;
import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user.repository.UserRepository;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import backend.sumnail.domain.user_nail_shop.service.UserNailShopService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final UserNailShopService userNailShopService;
    private final NailShopService nailShopService;
    private final RecentSearchService recentSearchService;

    @Transactional(readOnly = true)
    public UserFindResponse findUser(final long userId) {
        User user = userRepository.getById(userId);
        return UserFindResponse.from(user);
    }

    @Transactional(readOnly = true)
    public List<UserFindNailShopResponse> findAllNailShopsUser(final long userId) {
        List<UserNailShop> userNailShops = userNailShopService.findByUserId(userId);

        return userNailShops.stream()
                .map(userNailShop -> {
                    NailShopFindSavedDto savedNailShop = nailShopService.findSavedNailShop(userNailShop);
                    return UserFindNailShopResponse.from(savedNailShop);
                })
                .toList();
    }

    public void saveNailShopUser(long userId, long nailShopId) {
        User user = userRepository.getById(userId);
        NailShop nailShop = nailShopService.getById(nailShopId);
        userNailShopService.save(user, nailShop);
    }


    public void deleteNailShopUser(long userId, long nailShopId) {
        User user = userRepository.getById(userId);
        NailShop nailShop = nailShopService.getById(nailShopId);
        userNailShopService.delete(user, nailShop);
    }

    @Transactional(readOnly = true)
    public UserFindSearchStationsResponse findSearchStationsUser(long userId) {
        List<RecentSearch> recentSearches = recentSearchService.findByUserId(userId);
        List<RecentSearch> limitedRecentSearches= recentSearches.stream()
                .sorted(Comparator.comparing(RecentSearch::getDateTime).reversed())
                .limit(Math.min(recentSearches.size(),3))
                .toList();
        return UserFindSearchStationsResponse.from(limitedRecentSearches);
    }

    public void deleteSearchStationsUser(long userId) {
        recentSearchService.deleteAll(userId);
    }


}
