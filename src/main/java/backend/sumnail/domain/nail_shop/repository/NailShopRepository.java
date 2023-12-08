package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;

public interface NailShopRepository {
    NailShop getById(long id);
}
