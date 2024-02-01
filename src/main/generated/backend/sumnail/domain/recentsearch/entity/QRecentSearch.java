package backend.sumnail.domain.recentsearch.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecentSearch is a Querydsl query type for RecentSearch
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecentSearch extends EntityPathBase<RecentSearch> {

    private static final long serialVersionUID = -343172090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecentSearch recentSearch = new QRecentSearch("recentSearch");

    public final DateTimePath<java.time.LocalDateTime> dateTime = createDateTime("dateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath station = createString("station");

    public final backend.sumnail.domain.user.entity.QUser user;

    public QRecentSearch(String variable) {
        this(RecentSearch.class, forVariable(variable), INITS);
    }

    public QRecentSearch(Path<? extends RecentSearch> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecentSearch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecentSearch(PathMetadata metadata, PathInits inits) {
        this(RecentSearch.class, metadata, inits);
    }

    public QRecentSearch(Class<? extends RecentSearch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new backend.sumnail.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

