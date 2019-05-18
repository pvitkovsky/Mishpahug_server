package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHoliDayEntity is a Querydsl query type for HoliDayEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHoliDayEntity extends EntityPathBase<HoliDayEntity> {

    private static final long serialVersionUID = 1464700172L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHoliDayEntity holiDayEntity = new QHoliDayEntity("holiDayEntity");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QReligionEntity religionEntity;

    public QHoliDayEntity(String variable) {
        this(HoliDayEntity.class, forVariable(variable), INITS);
    }

    public QHoliDayEntity(Path<? extends HoliDayEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHoliDayEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHoliDayEntity(PathMetadata metadata, PathInits inits) {
        this(HoliDayEntity.class, metadata, inits);
    }

    public QHoliDayEntity(Class<? extends HoliDayEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.religionEntity = inits.isInitialized("religionEntity") ? new QReligionEntity(forProperty("religionEntity")) : null;
    }

}

