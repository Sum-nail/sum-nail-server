package backend.sumnail.domain.user_nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserNailShopRepositoryImpl implements UserNailShopRepository {
    private final UserNailShopJpaRepository userNailShopJpaRepository;

    @Override
    public List<UserNailShop> findByUserId(long userId) {
        return userNailShopJpaRepository.findByUserId(userId);
    }

    @Override
    public void save(UserNailShop userNailShop) {
        userNailShopJpaRepository.save(userNailShop);
    }

    @Override
    public void deleteByUserAndNailShop(User user, NailShop nailShop) {
        userNailShopJpaRepository.deleteByUserAndNailShop(user, nailShop);
    }

    @Override
    public Optional<UserNailShop> findByUserAndNailShop(User user, NailShop nailShop) {
        return userNailShopJpaRepository.findByUserAndNailShop(user, nailShop);
    }
}
