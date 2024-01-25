package backend.sumnail.domain.station.entity;

import backend.sumnail.global.util.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;

    private String stationName;

    @Convert(converter = StringListConverter.class)
    private List<String> line;

    @Builder
    public Station(Long id, String stationName, List<String> line) {
        this.id = id;
        this.stationName = stationName;
        this.line = line;
    }
}
