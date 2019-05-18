package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenderEntity is a Querydsl query type for GenderEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGenderEntity extends EntityPathBase<GenderEntity> {

    private static final long serialVersionUID = -825607917L;

    public static final QGenderEntity genderEntity = new QGenderEntity("genderEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QGenderEntity(String variable) {
        super(GenderEntity.class, forVariable(variable));
    }

    public QGenderEntity(Path<? extends GenderEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenderEntity(PathMetadata metadata) {
        super(GenderEntity.class, metadata);
    }

}

