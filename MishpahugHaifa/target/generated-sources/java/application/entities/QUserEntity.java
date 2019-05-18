package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1132477021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QAddressEntity addressEntity;

    public final DatePath<java.time.LocalDate> dateOfBirth = createDate("dateOfBirth", java.time.LocalDate.class);

    public final StringPath eMail = createString("eMail");

    public final StringPath encrytedPassword = createString("encrytedPassword");

    public final SetPath<EventEntity, QEventEntity> eventItemsOwner = this.<EventEntity, QEventEntity>createSet("eventItemsOwner", EventEntity.class, QEventEntity.class, PathInits.DIRECT2);

    public final StringPath firstName = createString("firstName");

    public final QGenderEntity gender;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QKitchenTypeEntity kitchenType;

    public final StringPath lastName = createString("lastName");

    public final QMaritalStatusEntity maritalStatus;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final SetPath<application.entities.values.PictureValue, application.entities.values.QPictureValue> pictureItems = this.<application.entities.values.PictureValue, application.entities.values.QPictureValue>createSet("pictureItems", application.entities.values.PictureValue.class, application.entities.values.QPictureValue.class, PathInits.DIRECT2);

    public final QReligionEntity religion;

    public final EnumPath<UserEntity.UserStatus> status = createEnum("status", UserEntity.UserStatus.class);

    public final SetPath<SubscriptionEntity, QSubscriptionEntity> subscriptions = this.<SubscriptionEntity, QSubscriptionEntity>createSet("subscriptions", SubscriptionEntity.class, QSubscriptionEntity.class, PathInits.DIRECT2);

    public final StringPath userName = createString("userName");

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressEntity = inits.isInitialized("addressEntity") ? new QAddressEntity(forProperty("addressEntity"), inits.get("addressEntity")) : null;
        this.gender = inits.isInitialized("gender") ? new QGenderEntity(forProperty("gender")) : null;
        this.kitchenType = inits.isInitialized("kitchenType") ? new QKitchenTypeEntity(forProperty("kitchenType")) : null;
        this.maritalStatus = inits.isInitialized("maritalStatus") ? new QMaritalStatusEntity(forProperty("maritalStatus")) : null;
        this.religion = inits.isInitialized("religion") ? new QReligionEntity(forProperty("religion")) : null;
    }

}

