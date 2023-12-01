package backend.sumnail.domain.user_nail_shop.repository;

import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNailShopJpaRepository extends JpaRepository<UserNailShop, Long> {
        List<UserNailShop> findByUserId(long userId);
}
