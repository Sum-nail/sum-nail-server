package backend.sumnail.domain.user_nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNailShopJpaRepository extends JpaRepository<UserNailShop, Long> {
    List<UserNailShop> findByUserId(long userId);

    void deleteByUserAndNailShop(User user, NailShop nailShop);

    Optional<UserNailShop> findByUserAndNailShop(User user, NailShop nailShop);
}
