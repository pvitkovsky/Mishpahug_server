package Application.models.feedback;

import Application.entities.FeedBackItem;

import java.util.List;

public interface IFeedBackModel {
    public List<FeedBackItem> getByEvent(Integer eventId);
    public List<FeedBackItem> getByUser(Integer userId);
    public List<FeedBackItem> removeByUser(Integer userId);
    public List<FeedBackItem> removeByEvent(Integer eventId);
}
