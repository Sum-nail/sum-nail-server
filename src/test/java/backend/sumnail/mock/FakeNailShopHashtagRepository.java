package backend.sumnail.mock;

import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.nail_shop_hashtag.repository.NailShopHashtagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class FakeNailShopHashtagRepository implements NailShopHashtagRepository {
    private final AtomicLong id = new AtomicLong(0);
    private final List<NailShopHashtag> data = new ArrayList<>();

    @Override
    public List<NailShopHashtag> getByNailShopId(Long id) {
        return data.stream()
                .filter(item -> item.getNailShop().getId().equals(id))
                .toList();
    }

    @Override
    public void save(NailShopHashtag nailShopHashtag) {
        if (nailShopHashtag.getId() == null || nailShopHashtag.getId() == 0) {
            NailShopHashtag newNailShopHashtag = NailShopHashtag.builder()
                    .nailShop(nailShopHashtag.getNailShop())
                    .hashtag(nailShopHashtag.getHashtag())
                    .build();
        } else {
            data.removeIf(it -> it.getId() == nailShopHashtag.getId());
        }
        data.add(nailShopHashtag);
    }
}
