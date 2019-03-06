package Application.models.feedback;

import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;
import Application.repo.EventRepository;
import Application.repo.FeedBackRepository;
import Application.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class FeedBackModel implements IFeedBackModel {

    @Autowired
    FeedBackRepository feedBackRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<FeedBackEntity> getAllByEvent(Integer eventId) {
        return eventRepository.getOne(eventId).getFeedBackEntities();
    }

    @Override
    public HashMap<Integer, FeedBackEntity> getAllByUser(UserEntity userEntity) {
        return userRepository.getOne(userEntity.getId()).getFeedBacks();
    }

    @Override
    public void removeAllByUser(UserEntity userEntity) {
        userRepository.getOne(userEntity.getId()).getFeedBacks().clear();
    }

    @Override
    public void removeAllByEvent(Integer eventId){
    }

    @Override
    public FeedBackEntity removeById(Integer feedBackId) {
        return null;
    }
}
