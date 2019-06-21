package application.models.feedback;

import application.entities.FeedBackValue;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class FeedBackModel implements IFeedBackModel {

    @Autowired
    SubscriptionRepository feedBackRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId) {
        Map<Integer, FeedBackValue> res = new HashMap<>();
        List<SubscriptionEntity> subscriptionEntityList = feedBackRepository.findByEvent_Id(eventId);
        //if (subscriptionEntityList != null) subscriptionEntityList.forEach(item -> res.put(item.getFeedback().hashCode(),item.getFeedback()));
        log.info("" + subscriptionEntityList);
        Integer z = 0;
        for (SubscriptionEntity x:subscriptionEntityList){
            FeedBackValue value = x.getFeedback();
            res.put(z,value);
            z++;
        }
        log.info("" + subscriptionEntityList);
        return res; //TODO: proper feedback please;
    }

    @Override
    public List<SubscriptionEntity> getEventsForGuest(UserEntity userEntity){
        return feedBackRepository.findByGuest_Id(userEntity.getId());
    }

    @Override
    public Map<Integer, FeedBackValue> getAllByUser(Integer userId) {
        Map<Integer, FeedBackValue> res = new HashMap<>();
        List<SubscriptionEntity> subscriptionEntityList = feedBackRepository.findByGuest_Id(userId);
        //if (subscriptionEntityList != null) subscriptionEntityList.forEach(item -> res.put(item.getFeedback().hashCode(),item.getFeedback()));
        log.info("" + subscriptionEntityList);
        Integer z = 0;
        for (SubscriptionEntity x:subscriptionEntityList){
            FeedBackValue value = x.getFeedback();
            res.put(z,value);
            z++;
        }
        return res; //TODO: proper feedback please;
    }

    @Override
    public void removeAllByUser(Integer userId) {
        feedBackRepository.removeById_UserGuestId(userId);
        //TODO: proper feedback please;
    }

    @Override
    public void removeAllByEvent(Integer eventId) {
        feedBackRepository.removeById_EventId(eventId);
        //TODO
    }

    @Override
    public FeedBackValue removeById(Integer feedBackId) {
        return null;
    }
}
