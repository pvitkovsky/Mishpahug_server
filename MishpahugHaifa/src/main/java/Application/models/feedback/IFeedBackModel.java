package Application.models.feedback;

import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;

import java.util.HashMap;
import java.util.List;

public interface IFeedBackModel {
    public List<FeedBackEntity> getAllByEvent(Integer eventId);
    public HashMap<Integer, FeedBackEntity> getAllByUser(UserEntity userEntity);
    public void removeAllByUser(UserEntity userEntity);
    public void removeAllByEvent(Integer eventId);
    public FeedBackEntity removeById(Integer feedBackId);
}
