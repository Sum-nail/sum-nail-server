package backend.sumnail.domain.user_nail_shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserNailShop is a Querydsl query type for UserNailShop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserNailShop extends EntityPathBase<UserNailShop> {

    private static final long serialVersionUID = 1296936642L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserNailShop userNailShop = new QUserNailShop("userNailShop");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final backend.sumnail.domain.nail_shop.entity.QNailShop nailShop;

    public final backend.sumnail.domain.user.entity.QUser user;

    public QUserNailShop(String variable) {
        this(UserNailShop.class, forVariable(variable), INITS);
    }

    public QUserNailShop(Path<? extends UserNailShop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserNailShop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserNailShop(PathMetadata metadata, PathInits inits) {
        this(UserNailShop.class, metadata, inits);
    }

    public QUserNailShop(Class<? extends UserNailShop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nailShop = inits.isInitialized("nailShop") ? new backend.sumnail.domain.nail_shop.entity.QNailShop(forProperty("nailShop")) : null;
        this.user = inits.isInitialized("user") ? new backend.sumnail.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

