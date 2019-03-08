package Application.models.feedback;

import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IFeedBackModel {
    public Map<Integer, FeedBackEntity> getAllByEvent(Integer eventId);
    public Map<Integer, FeedBackEntity> getAllByUser(UserEntity userEntity);  
    public void removeAllByUser(UserEntity userEntity);
    public void removeAllByEvent(Integer eventId);
    public FeedBackEntity removeById(Integer feedBackId);
}
