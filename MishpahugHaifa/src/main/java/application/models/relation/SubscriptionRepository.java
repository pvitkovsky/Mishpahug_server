package application.models.relation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import application.models.event.EventEntity;
import application.models.user.UserEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, SubscriptionRelation> {

	@Query("SELECT s.id.eventId FROM SubscriptionEntity s WHERE s.id.guestId = :guest")    
	public List<Integer> getEventIdsForGuestId(@Param(value = "guest") Integer guestId); 
	@Query("SELECT s.id.guestId FROM SubscriptionEntity s WHERE s.id.eventId = :event")    
	public List<Integer> getGuestIdsForEventId (@Param(value = "event") Integer eventId); 

	
	public List<SubscriptionEntity> findById_eventId(Integer eventId); 
	public List<SubscriptionEntity> findById_guestId(Integer guestId); 
	
	public void removeById_eventId(Integer eventId);
	public void removeById_guestId(Integer guestId);

}
