package application.repositories;

import application.models.event.EventEntity;
import application.models.relation.SubscriptionEntity;
import application.models.relation.SubscriptionEntity.EventGuestId;
import application.models.user.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, EventGuestId> {

	@Query("SELECT s.guest FROM SubscriptionEntity s WHERE s.event = :event")    
	public List<UserEntity> getGuestsForEvent (@Param(value = "event") EventEntity event); 
	@Query("SELECT s.event FROM SubscriptionEntity s WHERE s.guest = :guest")    
	public List<EventEntity> getEventsForGuest(@Param(value = "guest") UserEntity guest); 
	
	@Query("SELECT s.id.userGuestId FROM SubscriptionEntity s WHERE s.event = :event")    
	public List<Integer> getGuestIdsForEvent (@Param(value = "event") EventEntity event); 
	@Query("SELECT s.id.eventId FROM SubscriptionEntity s WHERE s.guest = :guest")    
	public List<Integer> getEventIdsForGuest(@Param(value = "guest") UserEntity guest); 
	
	public List<SubscriptionEntity> findByEvent_Id(Integer eventId); 
	public List<SubscriptionEntity> findByGuest_Id(Integer guestId); 
	
	public void removeById_EventId(Integer eventId);
	public void removeById_UserGuestId(Integer guestId);

}
