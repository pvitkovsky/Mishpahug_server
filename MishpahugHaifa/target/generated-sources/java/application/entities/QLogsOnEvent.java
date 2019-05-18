package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogsOnEvent is a Querydsl query type for LogsOnEvent
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogsOnEvent extends EntityPathBase<LogsOnEvent> {

    private static final long serialVersionUID = -1104232867L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogsOnEvent logsOnEvent = new QLogsOnEvent("logsOnEvent");

    public final QLogsDataEntity _super;

    public final EnumPath<LogsOnEvent.ActionsOnEvent> action = createEnum("action", LogsOnEvent.ActionsOnEvent.class);

    //inherited
    public final DatePath<java.time.LocalDate> date;

    //inherited
    public final StringPath description;

    public final QEventEntity eventTarget;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final TimePath<java.time.LocalTime> time;

    // inherited
    public final QUserEntity userActor;

    public QLogsOnEvent(String variable) {
        this(LogsOnEvent.class, forVariable(variable), INITS);
    }

    public QLogsOnEvent(Path<? extends LogsOnEvent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogsOnEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogsOnEvent(PathMetadata metadata, PathInits inits) {
        this(LogsOnEvent.class, metadata, inits);
    }

    public QLogsOnEvent(Class<? extends LogsOnEvent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QLogsDataEntity(type, metadata, inits);
        this.date = _super.date;
        this.description = _super.description;
        this.eventTarget = inits.isInitialized("eventTarget") ? new QEventEntity(forProperty("eventTarget"), inits.get("eventTarget")) : null;
        this.id = _super.id;
        this.time = _super.time;
        this.userActor = _super.userActor;
    }

}

