package application.entities.values;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPictureValue is a Querydsl query type for PictureValue
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPictureValue extends BeanPath<PictureValue> {

    private static final long serialVersionUID = -905598288L;

    public static final QPictureValue pictureValue = new QPictureValue("pictureValue");

    public final SimplePath<java.sql.Blob> data = createSimple("data", java.sql.Blob.class);

    public QPictureValue(String variable) {
        super(PictureValue.class, forVariable(variable));
    }

    public QPictureValue(Path<? extends PictureValue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPictureValue(PathMetadata metadata) {
        super(PictureValue.class, metadata);
    }

}

