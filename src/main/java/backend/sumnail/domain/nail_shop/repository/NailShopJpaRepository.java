package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NailShopJpaRepository extends JpaRepository<NailShop, Long> {
}
