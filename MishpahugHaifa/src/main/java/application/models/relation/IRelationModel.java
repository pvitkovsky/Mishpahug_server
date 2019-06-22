package application.models.relation;


import application.models.user.UserEntity;
import application.models.user.values.FeedBackValue;

import java.util.List;
import java.util.Map;

public interface IRelationModel {
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId);

    List<SubscriptionEntity> getEventsForGuest(UserEntity userEntity);

    public Map<Integer, FeedBackValue> getAllByUser(Integer userId);

    public void removeAllByUser(Integer userId);

    public void removeAllByEvent(Integer eventId);

    public FeedBackValue removeById(Integer feedBackId);
}
