package backend.sumnail.domain.nail_shop_hashtag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNailShopHashtag is a Querydsl query type for NailShopHashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNailShopHashtag extends EntityPathBase<NailShopHashtag> {

    private static final long serialVersionUID = 300481658L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNailShopHashtag nailShopHashtag = new QNailShopHashtag("nailShopHashtag");

    public final backend.sumnail.domain.hashtag.entity.QHashtag hashtag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final backend.sumnail.domain.nail_shop.entity.QNailShop nailShop;

    public QNailShopHashtag(String variable) {
        this(NailShopHashtag.class, forVariable(variable), INITS);
    }

    public QNailShopHashtag(Path<? extends NailShopHashtag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNailShopHashtag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNailShopHashtag(PathMetadata metadata, PathInits inits) {
        this(NailShopHashtag.class, metadata, inits);
    }

    public QNailShopHashtag(Class<? extends NailShopHashtag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashtag = inits.isInitialized("hashtag") ? new backend.sumnail.domain.hashtag.entity.QHashtag(forProperty("hashtag")) : null;
        this.nailShop = inits.isInitialized("nailShop") ? new backend.sumnail.domain.nail_shop.entity.QNailShop(forProperty("nailShop")) : null;
    }

}

