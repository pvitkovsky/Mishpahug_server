package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventEntity is a Querydsl query type for EventEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEventEntity extends EntityPathBase<EventEntity> {

    private static final long serialVersionUID = -1953366482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventEntity eventEntity = new QEventEntity("eventEntity");

    public final QAddressEntity addressEntity;

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final QHoliDayEntity holiDay;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QKitchenTypeEntity kitchenType;

    public final StringPath nameOfEvent = createString("nameOfEvent");

    public final EnumPath<EventEntity.EventStatus> status = createEnum("status", EventEntity.EventStatus.class);

    public final SetPath<SubscriptionEntity, QSubscriptionEntity> subscriptions = this.<SubscriptionEntity, QSubscriptionEntity>createSet("subscriptions", SubscriptionEntity.class, QSubscriptionEntity.class, PathInits.DIRECT2);

    public final TimePath<java.time.LocalTime> time = createTime("time", java.time.LocalTime.class);

    public final QUserEntity userEntityOwner;

    public QEventEntity(String variable) {
        this(EventEntity.class, forVariable(variable), INITS);
    }

    public QEventEntity(Path<? extends EventEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventEntity(PathMetadata metadata, PathInits inits) {
        this(EventEntity.class, metadata, inits);
    }

    public QEventEntity(Class<? extends EventEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressEntity = inits.isInitialized("addressEntity") ? new QAddressEntity(forProperty("addressEntity"), inits.get("addressEntity")) : null;
        this.holiDay = inits.isInitialized("holiDay") ? new QHoliDayEntity(forProperty("holiDay"), inits.get("holiDay")) : null;
        this.kitchenType = inits.isInitialized("kitchenType") ? new QKitchenTypeEntity(forProperty("kitchenType")) : null;
        this.userEntityOwner = inits.isInitialized("userEntityOwner") ? new QUserEntity(forProperty("userEntityOwner"), inits.get("userEntityOwner")) : null;
    }

}

