package backend.sumnail.domain.user.service;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.hashtag.repository.HashtagRepository;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;
import backend.sumnail.domain.nail_shop_hashtag.repository.NailShopHashtagRepository;
import backend.sumnail.domain.recentsearch.entity.RecentSearch;
import backend.sumnail.domain.recentsearch.repository.RecentSearchRepository;
import backend.sumnail.domain.user.controller.dto.response.UserFindNailShopResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindResponse;
import backend.sumnail.domain.user.controller.dto.response.UserFindSearchStationsResponse;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user.repository.UserRepository;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import backend.sumnail.domain.user_nail_shop.service.UserNailShopService;
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
    private final NailShopRepository nailShopRepository;
    private final NailShopHashtagRepository nailShopHashtagRepository;
    private final HashtagRepository hashtagRepository;
    private final RecentSearchRepository recentSearchRepository;

    @Transactional(readOnly = true)
    public UserFindResponse findUser(final long userId) {
        User user = userRepository.getById(userId);
        return UserFindResponse.from(user);
    }

    @Transactional(readOnly = true)
    public List<UserFindNailShopResponse> findAllNailShopsUser(final long userId) {
        List<UserNailShop> userNailShops = userNailShopService.findByUserId(userId);
        List<UserFindNailShopResponse> responses = userNailShops.stream()
                .map(userNailShop -> {
                    NailShop nailShop = nailShopRepository.getById(userNailShop.getId());
                    List<Hashtag> hashtags = nailShopHashtagRepository.getByNailShopId(nailShop.getId()).stream()
                            .map(nailShopHashtag -> hashtagRepository.getById(nailShopHashtag.getHashtag().getId()))
                            .toList();
                    return UserFindNailShopResponse.of(nailShop, hashtags);
                })
                .toList();

        return responses;
    }

    public void saveNailShopUser(long userId, long nailShopId) {
        User user = userRepository.getById(userId);
        NailShop nailShop = nailShopRepository.getById(nailShopId);

        userNailShopService.save(user, nailShop);
    }


    public void deleteNailShopUser(long userId, long nailShopId) {
        User user = userRepository.getById(userId);
        NailShop nailShop = nailShopRepository.getById(nailShopId);
        userNailShopService.delete(user, nailShop);
    }

    @Transactional(readOnly = true)
    public UserFindSearchStationsResponse findSearchStationsUser(long userId) {
        List<RecentSearch> recentSearches = recentSearchRepository.findByUserId(userId);
        return UserFindSearchStationsResponse.from(recentSearches);
    }

    public void deleteSearchStationsUser(long userId) {
        recentSearchRepository.deleteByUserId(userId);
    }


}
