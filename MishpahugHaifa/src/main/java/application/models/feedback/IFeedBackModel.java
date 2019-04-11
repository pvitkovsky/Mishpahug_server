package application.models.feedback;


import application.entities.UserEntity;
import application.entities.values.FeedBackValue;

import java.util.Map;

public interface IFeedBackModel {
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId);

    public Map<Integer, FeedBackValue> getAllByUser(UserEntity userEntity);

    public void removeAllByUser(UserEntity userEntity);

    public void removeAllByEvent(Integer eventId);

    public FeedBackValue removeById(Integer feedBackId);
}
