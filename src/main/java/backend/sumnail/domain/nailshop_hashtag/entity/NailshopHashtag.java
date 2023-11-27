package backend.sumnail.domain.nailshop_hashtag.entity;

import backend.sumnail.domain.hashtag.entity.Hashtag;
import backend.sumnail.domain.nailshop.entity.Nailshop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NailshopHashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nailshop_hashtag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nailshop_id")
    private Nailshop nailshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
