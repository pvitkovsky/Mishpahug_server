package application.models.feedback;

import application.entities.UserEntity;
import application.entities.values.FeedBackValue;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class FeedBackModel implements IFeedBackModel {

    @Autowired
    SubscriptionRepository feedBackRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId) {
        return null; //TODO: proper feedback please;
    }

    @Override
    public Map<Integer, FeedBackValue> getAllByUser(UserEntity userEntity) {
        return null; //TODO: proper feedback please;
    }

    @Override
    public void removeAllByUser(UserEntity userEntity) {
        //TODO: proper feedback please;
    }

    @Override
    public void removeAllByEvent(Integer eventId) {
    }

    @Override
    public FeedBackValue removeById(Integer feedBackId) {
        return null;
    }
}
