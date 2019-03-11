package Application.models.feedback;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;
import Application.repo.EventRepository;
import Application.repo.FeedBackRepository;
import Application.repo.UserRepository;
@Service
@Transactional
public class FeedBackModel implements IFeedBackModel {

    @Autowired
    FeedBackRepository feedBackRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Map<Integer, FeedBackEntity> getAllByEvent(Integer eventId) {
        return eventRepository.getOne(eventId).getFeedbacks();
    }

    @Override
    public Map<Integer, FeedBackEntity> getAllByUser(UserEntity userEntity) {
        return userRepository.getOne(userEntity.getId()).getFeedbacks();
    }

    @Override
    public void removeAllByUser(UserEntity userEntity) {
        userRepository.getOne(userEntity.getId()).getFeedbacks().clear();
    }

    @Override
    public void removeAllByEvent(Integer eventId){
    }

    @Override
    public FeedBackEntity removeById(Integer feedBackId) {
        return null;
    }
}
