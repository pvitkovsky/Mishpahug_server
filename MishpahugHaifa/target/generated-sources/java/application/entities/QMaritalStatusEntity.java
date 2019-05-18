package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMaritalStatusEntity is a Querydsl query type for MaritalStatusEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMaritalStatusEntity extends EntityPathBase<MaritalStatusEntity> {

    private static final long serialVersionUID = -455401574L;

    public static final QMaritalStatusEntity maritalStatusEntity = new QMaritalStatusEntity("maritalStatusEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QMaritalStatusEntity(String variable) {
        super(MaritalStatusEntity.class, forVariable(variable));
    }

    public QMaritalStatusEntity(Path<? extends MaritalStatusEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaritalStatusEntity(PathMetadata metadata) {
        super(MaritalStatusEntity.class, metadata);
    }

}

