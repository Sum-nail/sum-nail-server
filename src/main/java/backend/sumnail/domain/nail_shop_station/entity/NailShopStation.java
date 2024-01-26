package backend.sumnail.domain.nail_shop_station.entity;

import backend.sumnail.domain.nail_shop.entity.NailShop;
import backend.sumnail.domain.station.entity.Station;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NailShopStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nail_shop_station_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nail_shop_id")
    private NailShop nailShop;

    @Builder
    public NailShopStation(Station station, NailShop nailShop) {
        this.station = station;
        this.nailShop = nailShop;
    }

    public NailShopStation createNailShopStation(Station station, NailShop nailShop) {
        return NailShopStation.builder()
                .station(station)
                .nailShop(nailShop)
                .build();
    }
}
