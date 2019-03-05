package Application.models.feedback;

import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;
import Application.repo.EventRepository;
import Application.repo.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedBackModel implements IFeedBackModel {

    @Autowired
    FeedBackRepository feedBackRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<FeedBackEntity> getAllByEvent(Integer eventId) {
        return eventRepository.getOne(eventId).getFeedBackEntities();
    }

    @Override
    public List<FeedBackEntity> getAllByUser(UserEntity userEntity) {
        return feedBackRepository.getByUser(userEntity);
    }

    @Override
    public List<FeedBackEntity> removeAllByUser(UserEntity userEntity) {
        return feedBackRepository.removeByUser(userEntity);
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
