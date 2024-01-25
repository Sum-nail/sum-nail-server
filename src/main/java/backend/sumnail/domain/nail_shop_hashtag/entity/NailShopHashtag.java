package backend.sumnail.domain.nail_shop_hashtag.entity;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nail_shop.entity.NailShop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NailShopHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nail_shop_hashtag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nail_shop_id")
    private NailShop nailShop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    @Builder
    public NailShopHashtag(NailShop nailShop, Hashtag hashtag) {
        this.nailShop = nailShop;
        this.hashtag = hashtag;
    }

    public NailShopHashtag createNailShopHashtag(NailShop nailShop, Hashtag hashtag) {
        return NailShopHashtag.builder()
                .nailShop(nailShop)
                .hashtag(hashtag)
                .build();
    }
}
