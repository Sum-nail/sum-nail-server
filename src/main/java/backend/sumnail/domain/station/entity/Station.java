package backend.sumnail.domain.station.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Station {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="station_id")
    private Long id;

    private String stationName;

    private String line;
}
