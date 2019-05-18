package application.entities.values;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFeedBackValue is a Querydsl query type for FeedBackValue
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QFeedBackValue extends BeanPath<FeedBackValue> {

    private static final long serialVersionUID = 902839503L;

    public static final QFeedBackValue feedBackValue = new QFeedBackValue("feedBackValue");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> dateTime = createDateTime("dateTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public QFeedBackValue(String variable) {
        super(FeedBackValue.class, forVariable(variable));
    }

    public QFeedBackValue(Path<? extends FeedBackValue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeedBackValue(PathMetadata metadata) {
        super(FeedBackValue.class, metadata);
    }

}

