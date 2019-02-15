package Application.models.feedback;

import Application.entities.FeedBackEntity;
import Application.repo.FeedBackRepository;

import java.util.List;

public class FeedBackModel implements IFeedBackModel {

    FeedBackRepository freeBackRepository;

    @Override
    public List<FeedBackEntity> getAllByEvent(Integer eventId) {
        return null;
    }

    @Override
    public List<FeedBackEntity> getAllByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FeedBackEntity> removeAllByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FeedBackEntity> removeAllByEvent(Integer eventId) {
        return null;
    }

    @Override
    public FeedBackEntity removeById(Integer feedBackId) {
        return null;
    }
}
