package Application.models.feedback;

import Application.entities.FeedBackEntity;
import Application.repo.EventRepository;
import Application.repo.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedBackModel implements IFeedBackModel {

    @Autowired
    FeedBackRepository freeBackRepository;

    @Autowired
    EventRepository eventRepository;

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
