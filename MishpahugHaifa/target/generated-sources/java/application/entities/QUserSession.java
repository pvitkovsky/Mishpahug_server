package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserSession is a Querydsl query type for UserSession
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserSession extends EntityPathBase<UserSession> {

    private static final long serialVersionUID = 28900188L;

    public static final QUserSession userSession = new QUserSession("userSession");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ip = createString("ip");

    public final BooleanPath isValid = createBoolean("isValid");

    public final DatePath<org.joda.time.LocalDate> localDate = createDate("localDate", org.joda.time.LocalDate.class);

    public final TimePath<org.joda.time.LocalTime> localTime = createTime("localTime", org.joda.time.LocalTime.class);

    public final StringPath token = createString("token");

    public final StringPath userAgent = createString("userAgent");

    public final StringPath userEntity = createString("userEntity");

    public QUserSession(String variable) {
        super(UserSession.class, forVariable(variable));
    }

    public QUserSession(Path<? extends UserSession> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserSession(PathMetadata metadata) {
        super(UserSession.class, metadata);
    }

}

