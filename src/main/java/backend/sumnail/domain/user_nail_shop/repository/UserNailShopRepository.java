package backend.sumnail.domain.user_nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;

public interface UserNailShopRepository {
    List<UserNailShop> findByUserId(long userId);

    void save(UserNailShop userNailShop);

    void deleteByUserAndNailShop(User user, NailShop nailShop);
}
