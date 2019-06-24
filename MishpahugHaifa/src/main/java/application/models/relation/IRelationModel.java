package application.models.relation;


import application.models.event.EventEntity;
import application.models.user.UserEntity;
import application.models.user.values.FeedBackValue;

import java.util.List;
import java.util.Map;

public interface IRelationModel {
	
	public List<Integer> getSubscribedGuests_Ids(Integer eventId);
	
    public List<Integer> getSubscribedEvents_Ids(Integer guestId); 

    public void subscribe(UserEntity guest, EventEntity event); 
    
    public void deactivateSubscription(Integer eventId, Integer userId); 
   
    public Map<Integer, FeedBackValue> getAllByEvent(Integer eventId);

    public Map<Integer, FeedBackValue> getAllByUser(Integer userId);

    public void removeAllByUser(Integer userId);

    public void removeAllByEvent(Integer eventId);

    public FeedBackValue removeById(Integer feedBackId);
}
