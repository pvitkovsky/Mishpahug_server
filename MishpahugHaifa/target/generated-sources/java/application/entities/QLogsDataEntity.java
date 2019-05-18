package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogsDataEntity is a Querydsl query type for LogsDataEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogsDataEntity extends EntityPathBase<LogsDataEntity> {

    private static final long serialVersionUID = 1070870155L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogsDataEntity logsDataEntity = new QLogsDataEntity("logsDataEntity");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> time = createTime("time", java.time.LocalTime.class);

    public final QUserEntity userActor;

    public QLogsDataEntity(String variable) {
        this(LogsDataEntity.class, forVariable(variable), INITS);
    }

    public QLogsDataEntity(Path<? extends LogsDataEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogsDataEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogsDataEntity(PathMetadata metadata, PathInits inits) {
        this(LogsDataEntity.class, metadata, inits);
    }

    public QLogsDataEntity(Class<? extends LogsDataEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userActor = inits.isInitialized("userActor") ? new QUserEntity(forProperty("userActor"), inits.get("userActor")) : null;
    }

}

