package backend.sumnail.domain.station.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStation is a Querydsl query type for Station
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStation extends EntityPathBase<Station> {

    private static final long serialVersionUID = 48867398L;

    public static final QStation station = new QStation("station");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<String, StringPath> line = this.<String, StringPath>createList("line", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath stationName = createString("stationName");

    public QStation(String variable) {
        super(Station.class, forVariable(variable));
    }

    public QStation(Path<? extends Station> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStation(PathMetadata metadata) {
        super(Station.class, metadata);
    }

}

