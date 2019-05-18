package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogsOnUser is a Querydsl query type for LogsOnUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogsOnUser extends EntityPathBase<LogsOnUser> {

    private static final long serialVersionUID = 1904516008L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogsOnUser logsOnUser = new QLogsOnUser("logsOnUser");

    public final QLogsDataEntity _super;

    public final EnumPath<LogsOnUser.ActionsOnUser> action = createEnum("action", LogsOnUser.ActionsOnUser.class);

    //inherited
    public final DatePath<java.time.LocalDate> date;

    //inherited
    public final StringPath description;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final TimePath<java.time.LocalTime> time;

    // inherited
    public final QUserEntity userActor;

    public final QUserEntity userTarget;

    public QLogsOnUser(String variable) {
        this(LogsOnUser.class, forVariable(variable), INITS);
    }

    public QLogsOnUser(Path<? extends LogsOnUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogsOnUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogsOnUser(PathMetadata metadata, PathInits inits) {
        this(LogsOnUser.class, metadata, inits);
    }

    public QLogsOnUser(Class<? extends LogsOnUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QLogsDataEntity(type, metadata, inits);
        this.date = _super.date;
        this.description = _super.description;
        this.id = _super.id;
        this.time = _super.time;
        this.userActor = _super.userActor;
        this.userTarget = inits.isInitialized("userTarget") ? new QUserEntity(forProperty("userTarget"), inits.get("userTarget")) : null;
    }

}

