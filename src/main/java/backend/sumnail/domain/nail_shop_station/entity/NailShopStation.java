package backend.sumnail.domain.nail_shop_station.entity;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.station.entity.Station;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NailShopStation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nail_shop_station_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nail_shop_id")
    private NailShop nailShop;
}
