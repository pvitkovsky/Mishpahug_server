package application.models.relation;


import application.models.event.EventEntity;
import application.models.user.UserEntity;
import application.models.user.values.FeedBackValue;

import java.util.List;
import java.util.Map;

public interface IRelationModel {
	
	List<Integer> getEventIdsByGuest(UserEntity guest);

	List<Integer> getGuestIdsByEvent(EventEntity event);

	List<EventEntity> getSubscribedEvents(Integer userId);

	List<UserEntity> getSubscribedGuests(Integer eventId);
	
    public void subscribe(Integer eventId, Integer userId); 
    
    public void unsubscribe(Integer eventId, Integer userId); 
   
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId);

    public Map<Integer, FeedBackValue> getAllByUser(Integer userId);

    public void removeAllByUser(Integer userId);

    public void removeAllByEvent(Integer eventId);

    public FeedBackValue removeById(Integer feedBackId);


}
