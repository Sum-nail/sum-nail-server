package backend.sumnail.domain.user_nail_shop.service;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user_nail_shop.entity.UserNailShop;
import backend.sumnail.domain.user_nail_shop.repository.UserNailShopRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserNailShopService {
    UserNailShopRepository userNailShopRepository;

    public void delete(User user, NailShop nailShop) {
        throwIfNailShopNotSaved(user, nailShop);
        userNailShopRepository.deleteByUserAndNailShop(user, nailShop);
    }

    public void save(User user, NailShop nailShop) {
        throwIfNailShopAlreadySaved(user, nailShop);
        UserNailShop userNailShop = UserNailShop.createUserNailShop(user, nailShop);
        userNailShopRepository.save(userNailShop);
    }

    public List<UserNailShop> findByUserId(long userId) {
        return userNailShopRepository.findByUserId(userId);
    }

    private void throwIfNailShopAlreadySaved(User user, NailShop nailShop) {
        userNailShopRepository.findByUserAndNailShop(user, nailShop)
                .ifPresent((userNailShop) -> new RuntimeException("이미 저장한 네일샵입니다"));
    }

    private void throwIfNailShopNotSaved(User user, NailShop nailShop) {
        userNailShopRepository.findByUserAndNailShop(user, nailShop)
                .orElseThrow(() -> new RuntimeException("저장하지 않은 네일샵입니다."));
    }


}
