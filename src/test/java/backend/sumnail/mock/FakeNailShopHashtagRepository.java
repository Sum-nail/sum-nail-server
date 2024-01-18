package backend.sumnail.mock;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.nail_shop_hashtag.repository.NailShopHashtagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class FakeNailShopHashtagRepository implements NailShopHashtagRepository {
    private final Long id = new AtomicLong().getAndIncrement();
    private final List<NailShopHashtag> data = new ArrayList<>();

    @Override
    public List<NailShopHashtag> getByNailShopId(Long id) {
        return data.stream()
                .filter(item -> item.getNailShop().getId().equals(id))
                .toList();
    }

    public void save(NailShopHashtag nailShopHashtag) {
        if (nailShopHashtag.getId() == null || nailShopHashtag.getId() == 0) {
            NailShop nailShop = NailShop.builder()
                    .id(1L)
                    .location("서울시 중구")
                    .name("썸네일네일샵")
                    .titleImage("http://zoom.us?g=1")
                    .build();
            Hashtag hashtag = Hashtag.builder()
                    .id(1L)
                    .hashtagName("심플한")
                    .build();
            NailShopHashtag newNailShopHashtag = NailShopHashtag.builder()
                    .nailShop(nailShop)
                    .hashtag(hashtag)
                    .build();
            data.add(newNailShopHashtag);
        } else {
            data.removeIf(it -> Objects.equals(it.getId(), nailShopHashtag.getId()));
            data.add(nailShopHashtag);
        }
    }
}