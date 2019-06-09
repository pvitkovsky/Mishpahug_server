package application.models.feedback;


import java.util.List;
import java.util.Map;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.entities.values.FeedBackValue;

public interface IFeedBackModel {
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId);

    List<SubscriptionEntity> getEventsForGuest(UserEntity userEntity);

    public Map<Integer, FeedBackValue> getAllByUser(Integer userId);

    public void removeAllByUser(Integer userId);

    public void removeAllByEvent(Integer eventId);

    public FeedBackValue removeById(Integer feedBackId);
}
