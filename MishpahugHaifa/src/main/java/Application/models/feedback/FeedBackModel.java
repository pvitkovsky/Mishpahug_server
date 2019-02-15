package Application.models.feedback;

import Application.entities.FeedBackItem;
import Application.repo.FeedBackRepository;

import java.util.List;

public class FeedBackModel implements IFeedBackModel {

    FeedBackRepository freeBackRepository;

    @Override
    public List<FeedBackItem> getAllByEvent(Integer eventId) {
        return null;
    }

    @Override
    public List<FeedBackItem> getAllByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FeedBackItem> removeAllByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FeedBackItem> removeAllByEvent(Integer eventId) {
        return null;
    }

    @Override
    public FeedBackItem removeById(Integer feedBackId) {
        return null;
    }
}
