package application.models.feedback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.UserEntity;
import application.entities.values.FeedBackValue;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;

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
        if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
        return null; //TODO: proper feedback please;
    }

    @Override
    public List<SubscriptionEntity> getEventsForGuest(UserEntity userEntity){
        return feedBackRepository.findByGuest(userEntity);
    }

    @Override
    public Map<Integer, FeedBackValue> getAllByUser(UserEntity userEntity) {
        Map<Integer, FeedBackValue> res = new HashMap<>();
        List<SubscriptionEntity> subscriptionEntityList = feedBackRepository.findByGuest(userEntity);
        subscriptionEntityList.forEach(item -> res.put(item.getFeedback().hashCode(),item.getFeedback()));
        return null; //TODO: proper feedback please;
    }

    @Override
    public void removeAllByUser(UserEntity userEntity) {
        //TODO: proper feedback please;
    }

    @Override
    public void removeAllByEvent(Integer eventId) {
        if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
        //TODO
    }

    @Override
    public FeedBackValue removeById(Integer feedBackId) {
        return null;
    }
}
