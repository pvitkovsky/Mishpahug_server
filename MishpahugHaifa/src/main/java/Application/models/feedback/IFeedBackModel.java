package Application.models.feedback;

import Application.entities.FeedBackItem;

import java.util.List;

public interface IFeedBackModel {
    public List<FeedBackItem> getAllByEvent(Integer eventId);
    public List<FeedBackItem> getAllByUser(Integer userId);
    public List<FeedBackItem> removeAllByUser(Integer userId);
    public List<FeedBackItem> removeAllByEvent(Integer eventId);
    public FeedBackItem removeById(Integer feedBackId);
}
