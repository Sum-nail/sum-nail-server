package backend.sumnail.domain.nailshop_station.entity;

import backend.sumnail.domain.nailshop.entity.Nailshop;
import backend.sumnail.domain.station.entity.Station;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NailshopStation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nailshop_station_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nailshop_id")
    private Nailshop nailshop;
}
