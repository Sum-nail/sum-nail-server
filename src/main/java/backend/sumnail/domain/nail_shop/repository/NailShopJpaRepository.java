package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NailShopJpaRepository extends JpaRepository<NailShop, Long> {

    Optional<NailShop> findById(Long id);
}
