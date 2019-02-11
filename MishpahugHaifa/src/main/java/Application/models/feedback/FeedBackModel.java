package Application.models.feedback;

import Application.entities.FeedBackItem;
import Application.repo.FeedBackRepository;

import java.util.List;

public class FeedBackModel implements IFeedBackModel {

    FeedBackRepository freeBackRepository;

    @Override
    public List<FeedBackItem> getByEvent(Integer eventId) {
        return null;
    }

    @Override
    public List<FeedBackItem> getByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FeedBackItem> removeByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FeedBackItem> removeByEvent(Integer eventId) {
        return null;
    }
}
