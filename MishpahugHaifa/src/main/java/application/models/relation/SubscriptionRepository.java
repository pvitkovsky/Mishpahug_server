package application.models.relation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import application.models.event.EventEntity;
import application.models.relation.SubscriptionEntity.EventGuestId;
import application.models.user.UserEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, EventGuestId> {

	
	//Violates inter-aggregate boundaries; 
//	@Query("SELECT s.guest FROM SubscriptionEntity s WHERE s.event = :event")    
//	public List<UserEntity> getGuestsForEvent (@Param(value = "event") EventEntity event); 
//	@Query("SELECT s.event FROM SubscriptionEntity s WHERE s.guest = :guest")    
//	public List<EventEntity> getEventsForGuest(@Param(value = "guest") UserEntity guest); 
	
	//TODO: test these;
	@Query("SELECT s.guest FROM SubscriptionEntity s WHERE s.event.id = :event")    
	public List<UserEntity> getGuestsForEventId (@Param(value = "event") Integer eventId); 
	@Query("SELECT s.event FROM SubscriptionEntity s WHERE s.guest.id = :guest")    
	public List<EventEntity> getEventsForGuestId(@Param(value = "guest") Integer guestId); 
	
	
	public List<SubscriptionEntity> findByEvent_Id(Integer eventId); 
	public List<SubscriptionEntity> findByGuest_Id(Integer guestId); 
	
	public void removeById_EventId(Integer eventId);
	public void removeById_UserGuestId(Integer guestId);

}
