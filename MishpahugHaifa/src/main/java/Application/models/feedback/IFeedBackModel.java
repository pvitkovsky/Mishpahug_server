package Application.models.feedback;

import Application.entities.FeedBackEntity;

import java.util.List;

public interface IFeedBackModel {
    public List<FeedBackEntity> getAllByEvent(Integer eventId);
    public List<FeedBackEntity> getAllByUser(Integer userId);
    public List<FeedBackEntity> removeAllByUser(Integer userId);
    public List<FeedBackEntity> removeAllByEvent(Integer eventId);
    public FeedBackEntity removeById(Integer feedBackId);
}
