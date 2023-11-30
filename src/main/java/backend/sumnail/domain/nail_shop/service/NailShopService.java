package backend.sumnail.domain.nail_shop.service;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop.repository.NailShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NailShopService {

    private final NailShopRepository nailShopRepository;

    public List<NailShop> findAllShop(){
        return nailShopRepository.findAll();
    }


}
