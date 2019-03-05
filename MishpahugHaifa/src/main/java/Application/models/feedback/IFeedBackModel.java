package Application.models.feedback;

import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;

import java.util.List;

public interface IFeedBackModel {
    public List<FeedBackEntity> getAllByEvent(Integer eventId);
    public List<FeedBackEntity> getAllByUser(UserEntity userEntity);
    public List<FeedBackEntity> removeAllByUser(UserEntity userEntity);
    public List<FeedBackEntity> removeAllByEvent(Integer eventId);
    public FeedBackEntity removeById(Integer feedBackId);
}
