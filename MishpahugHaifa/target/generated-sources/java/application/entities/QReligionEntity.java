package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReligionEntity is a Querydsl query type for ReligionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReligionEntity extends EntityPathBase<ReligionEntity> {

    private static final long serialVersionUID = 1072363459L;

    public static final QReligionEntity religionEntity = new QReligionEntity("religionEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QReligionEntity(String variable) {
        super(ReligionEntity.class, forVariable(variable));
    }

    public QReligionEntity(Path<? extends ReligionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReligionEntity(PathMetadata metadata) {
        super(ReligionEntity.class, metadata);
    }

}

