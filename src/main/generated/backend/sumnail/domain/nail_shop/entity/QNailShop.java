package backend.sumnail.domain.nail_shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNailShop is a Querydsl query type for NailShop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNailShop extends EntityPathBase<NailShop> {

    private static final long serialVersionUID = -1983187355L;

    public static final QNailShop nailShop = new QNailShop("nailShop");

    public final StringPath businessHour = createString("businessHour");

    public final ListPath<String, StringPath> detailImages = this.<String, StringPath>createList("detailImages", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> employeeNum = createNumber("employeeNum", Long.class);

    public final ListPath<backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag, backend.sumnail.domain.nail_shop_hashtag.entity.QNailShopHashtag> hashtags = this.<backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag, backend.sumnail.domain.nail_shop_hashtag.entity.QNailShopHashtag>createList("hashtags", backend.sumnail.domain.nail_shop_hashtag.entity.NailShopHashtag.class, backend.sumnail.domain.nail_shop_hashtag.entity.QNailShopHashtag.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final NumberPath<Double> mapLat = createNumber("mapLat", Double.class);

    public final NumberPath<Double> mapLng = createNumber("mapLng", Double.class);

    public final NumberPath<Long> maximumPrice = createNumber("maximumPrice", Long.class);

    public final NumberPath<Long> minimumPrice = createNumber("minimumPrice", Long.class);

    public final StringPath monthlyNailLink = createString("monthlyNailLink");

    public final StringPath name = createString("name");

    public final StringPath naverMapLink = createString("naverMapLink");

    public final StringPath reservationTable = createString("reservationTable");

    public final ListPath<backend.sumnail.domain.nail_shop_station.entity.NailShopStation, backend.sumnail.domain.nail_shop_station.entity.QNailShopStation> stations = this.<backend.sumnail.domain.nail_shop_station.entity.NailShopStation, backend.sumnail.domain.nail_shop_station.entity.QNailShopStation>createList("stations", backend.sumnail.domain.nail_shop_station.entity.NailShopStation.class, backend.sumnail.domain.nail_shop_station.entity.QNailShopStation.class, PathInits.DIRECT2);

    public final StringPath streetAddress = createString("streetAddress");

    public final StringPath titleImage = createString("titleImage");

    public QNailShop(String variable) {
        super(NailShop.class, forVariable(variable));
    }

    public QNailShop(Path<? extends NailShop> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNailShop(PathMetadata metadata) {
        super(NailShop.class, metadata);
    }

}

