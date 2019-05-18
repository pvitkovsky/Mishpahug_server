package application.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubscriptionEntity_EventGuestId is a Querydsl query type for EventGuestId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QSubscriptionEntity_EventGuestId extends BeanPath<SubscriptionEntity.EventGuestId> {

    private static final long serialVersionUID = -1873558120L;

    public static final QSubscriptionEntity_EventGuestId eventGuestId = new QSubscriptionEntity_EventGuestId("eventGuestId");

    public final NumberPath<Integer> eventId = createNumber("eventId", Integer.class);

    public final NumberPath<Integer> userGuestId = createNumber("userGuestId", Integer.class);

    public QSubscriptionEntity_EventGuestId(String variable) {
        super(SubscriptionEntity.EventGuestId.class, forVariable(variable));
    }

    public QSubscriptionEntity_EventGuestId(Path<? extends SubscriptionEntity.EventGuestId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubscriptionEntity_EventGuestId(PathMetadata metadata) {
        super(SubscriptionEntity.EventGuestId.class, metadata);
    }

}

