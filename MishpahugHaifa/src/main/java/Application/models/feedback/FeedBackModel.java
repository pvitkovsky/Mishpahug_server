package Application.models.feedback;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.entities.UserEntity;
import Application.entities.values.FeedBackValue;
import Application.repo.EventRepository;
import Application.repo.EventGuestRepository;
import Application.repo.UserRepository;
@Service
@Transactional
public class FeedBackModel implements IFeedBackModel {

    @Autowired
    EventGuestRepository feedBackRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId) {
        return null; //TODO: eventRepository.getOne(eventId).getFeedbacks();
    }

    @Override
    public Map<Integer, FeedBackValue> getAllByUser(UserEntity userEntity) {
        return null; //TODO: userRepository.getOne(userEntity.getId()).getFeedbacks();
    }

    @Override
    public void removeAllByUser(UserEntity userEntity) {
        ////TODO: userRepository.getOne(userEntity.getId()).getFeedbacks().clear();
    }

    @Override
    public void removeAllByEvent(Integer eventId){
    }

    @Override
    public FeedBackValue removeById(Integer feedBackId) {
        return null;
    }
}
