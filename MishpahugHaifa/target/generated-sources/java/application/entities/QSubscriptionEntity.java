package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscriptionEntity is a Querydsl query type for SubscriptionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubscriptionEntity extends EntityPathBase<SubscriptionEntity> {

    private static final long serialVersionUID = -607345873L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscriptionEntity subscriptionEntity = new QSubscriptionEntity("subscriptionEntity");

    public final QEventEntity event;

    public final application.entities.values.QFeedBackValue feedback;

    public final QUserEntity guest;

    public final QSubscriptionEntity_EventGuestId id;

    public final EnumPath<SubscriptionEntity.SubscriptionStatus> status = createEnum("status", SubscriptionEntity.SubscriptionStatus.class);

    public QSubscriptionEntity(String variable) {
        this(SubscriptionEntity.class, forVariable(variable), INITS);
    }

    public QSubscriptionEntity(Path<? extends SubscriptionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscriptionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscriptionEntity(PathMetadata metadata, PathInits inits) {
        this(SubscriptionEntity.class, metadata, inits);
    }

    public QSubscriptionEntity(Class<? extends SubscriptionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEventEntity(forProperty("event"), inits.get("event")) : null;
        this.feedback = inits.isInitialized("feedback") ? new application.entities.values.QFeedBackValue(forProperty("feedback")) : null;
        this.guest = inits.isInitialized("guest") ? new QUserEntity(forProperty("guest"), inits.get("guest")) : null;
        this.id = inits.isInitialized("id") ? new QSubscriptionEntity_EventGuestId(forProperty("id")) : null;
    }

}

