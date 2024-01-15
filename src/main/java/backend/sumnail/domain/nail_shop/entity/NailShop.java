package backend.sumnail.domain.nail_shop.entity;

import backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag;
import backend.sumnail.domain.nail_shop_station.entity.NailShopStation;
import backend.sumnail.global.util.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NailShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nail_shop_id")
    private Long id;

    @Column(name = "nail_shop_name")
    private String name;

    private String location;

////    @Convert(converter = PointConverter.class)
//    @Column(columnDefinition = "Geometry")
//    private Geometry coordinate;

    private Double mapLat;

    private Double mapLng;

    private Long employeeNum;

    private String businessHour;

    private String reservationTable;

    private String naverMapLink;

    private String streetAddress;

    @Column(name = "monthly_nail_instagram_link")
    private String monthlyNailLink;

    @Column(name = "monthly_nail_minimum_price")
    private Long minimumPrice;

    @Column(name = "monthly_nail_maximum_price")
    private Long maximumPrice;

    private String titleImage;

    @OneToMany(mappedBy = "nailShop")
    private List<NailShopHashtag> hashtags;

    @OneToMany(mappedBy = "nailShop")
    private List<NailShopStation> stations;

    @Convert(converter = StringListConverter.class)
    private List<String> detailImages;

    @Builder
    public NailShop(Long id,String name, String location, Double mapLat, Double mapLng, Long employeeNum,
        String businessHour, String reservationTable, String naverMapLink, String streetAddress,
        String monthlyNailLink, Long minimumPrice, Long maximumPrice, String titleImage,
        List<NailShopHashtag> hashtags, List<NailShopStation> stations, List<String> detailImages) {
        this.id=id;
        this.name = name;
        this.location = location;
        this.mapLat = mapLat;
        this.mapLng = mapLng;
        this.employeeNum = employeeNum;
        this.businessHour = businessHour;
        this.reservationTable = reservationTable;
        this.naverMapLink = naverMapLink;
        this.streetAddress = streetAddress;
        this.monthlyNailLink = monthlyNailLink;
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
        this.titleImage = titleImage;
        this.hashtags = hashtags;
        this.stations = stations;
        this.detailImages = detailImages;
    }
}
