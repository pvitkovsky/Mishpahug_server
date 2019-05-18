package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QKitchenTypeEntity is a Querydsl query type for KitchenTypeEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKitchenTypeEntity extends EntityPathBase<KitchenTypeEntity> {

    private static final long serialVersionUID = 126568658L;

    public static final QKitchenTypeEntity kitchenTypeEntity = new QKitchenTypeEntity("kitchenTypeEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QKitchenTypeEntity(String variable) {
        super(KitchenTypeEntity.class, forVariable(variable));
    }

    public QKitchenTypeEntity(Path<? extends KitchenTypeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKitchenTypeEntity(PathMetadata metadata) {
        super(KitchenTypeEntity.class, metadata);
    }

}

