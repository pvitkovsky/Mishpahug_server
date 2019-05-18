package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCountryEntity is a Querydsl query type for CountryEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCountryEntity extends EntityPathBase<CountryEntity> {

    private static final long serialVersionUID = 1660990954L;

    public static final QCountryEntity countryEntity = new QCountryEntity("countryEntity");

    public final SetPath<CityEntity, QCityEntity> cityEntities = this.<CityEntity, QCityEntity>createSet("cityEntities", CityEntity.class, QCityEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QCountryEntity(String variable) {
        super(CountryEntity.class, forVariable(variable));
    }

    public QCountryEntity(Path<? extends CountryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountryEntity(PathMetadata metadata) {
        super(CountryEntity.class, metadata);
    }

}

