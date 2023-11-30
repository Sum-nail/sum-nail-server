package backend.sumnail.domain.nail_shop.repository;

import backend.sumnail.domain.nail_shop.entity.NailShop;


import java.util.List;


public interface NailShopRepository{
    List<NailShop> findAll();
}