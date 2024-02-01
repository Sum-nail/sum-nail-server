package backend.sumnail.domain.nail_shop_station.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNailShopStation is a Querydsl query type for NailShopStation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNailShopStation extends EntityPathBase<NailShopStation> {

    private static final long serialVersionUID = 1527605114L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNailShopStation nailShopStation = new QNailShopStation("nailShopStation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final backend.sumnail.domain.nail_shop.entity.QNailShop nailShop;

    public final backend.sumnail.domain.station.entity.QStation station;

    public QNailShopStation(String variable) {
        this(NailShopStation.class, forVariable(variable), INITS);
    }

    public QNailShopStation(Path<? extends NailShopStation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNailShopStation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNailShopStation(PathMetadata metadata, PathInits inits) {
        this(NailShopStation.class, metadata, inits);
    }

    public QNailShopStation(Class<? extends NailShopStation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nailShop = inits.isInitialized("nailShop") ? new backend.sumnail.domain.nail_shop.entity.QNailShop(forProperty("nailShop")) : null;
        this.station = inits.isInitialized("station") ? new backend.sumnail.domain.station.entity.QStation(forProperty("station")) : null;
    }

}

