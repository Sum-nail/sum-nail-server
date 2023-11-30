package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NailShopJpaRepository extends JpaRepository<NailShop,Long> {
    List<NailShop> findAll();
}
