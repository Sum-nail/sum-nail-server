package backend.sumnail.domain.user_nail_shop.repository;

import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class UserNailShopRepositoryImpl implements UserNailShopRepository{
    private final UserNailShopJpaRepository userNailShopJpaRepository;

    @Override
    public List<UserNailShop> findByUserId(long userId) {
        return userNailShopJpaRepository.findByUserId(userId);
    }
}
