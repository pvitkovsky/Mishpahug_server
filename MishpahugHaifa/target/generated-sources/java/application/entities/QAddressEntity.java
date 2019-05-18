package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddressEntity is a Querydsl query type for AddressEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddressEntity extends EntityPathBase<AddressEntity> {

    private static final long serialVersionUID = -1714963512L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddressEntity addressEntity = new QAddressEntity("addressEntity");

    public final NumberPath<Integer> apartment = createNumber("apartment", Integer.class);

    public final NumberPath<Integer> building = createNumber("building", Integer.class);

    public final QCityEntity cityEntity;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath street = createString("street");

    public QAddressEntity(String variable) {
        this(AddressEntity.class, forVariable(variable), INITS);
    }

    public QAddressEntity(Path<? extends AddressEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddressEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddressEntity(PathMetadata metadata, PathInits inits) {
        this(AddressEntity.class, metadata, inits);
    }

    public QAddressEntity(Class<? extends AddressEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cityEntity = inits.isInitialized("cityEntity") ? new QCityEntity(forProperty("cityEntity"), inits.get("cityEntity")) : null;
    }

}

