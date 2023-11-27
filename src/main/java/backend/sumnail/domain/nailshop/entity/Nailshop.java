package backend.sumnail.domain.nailshop.entity;

import backend.sumnail.global.util.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nailshop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nailshop_id")
    private Long id;

    @Column(name = "nailshop_name")
    private String name;

    private String location;

    private Point coordinate;

    private Long employeeNum;

    private String businessHour;

    private String reservationTable;

    private String naverMapLink;

    private String streetAddress;

    @Column(name = "monthly_nail_instagram_link")
    private String monthlyNailLink;

    @Column(name="monthly_nail_minimum_price")
    private Long minimumPrice;

    @Column(name="monthly_nail_maximum_price")
    private Long maximumPrice;

    private String titleImage;

    @Convert(converter = StringListConverter.class)
    private List<String> detailImages;
}
