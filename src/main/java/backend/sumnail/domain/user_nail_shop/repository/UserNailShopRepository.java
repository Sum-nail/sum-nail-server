package backend.sumnail.domain.user_nail_shop.repository;

import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;

public interface UserNailShopRepository {
    List<UserNailShop> findByUserId(long userId);
}
