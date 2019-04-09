package application.repositories;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.SubscriptionEntity.EventGuestId;
import application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, EventGuestId> {


	@Query("SELECT s.guest FROM SubscriptionEntity s WHERE s.event = :event")    
	public List<UserEntity> getGuestsForEvent (@Param(value = "event") EventEntity event); 
	
	public List<UserEntity> findByEvent(EventEntity event); //TODO: converter; 


	@Query("SELECT s.event FROM SubscriptionEntity s WHERE s.guest = :guest")    
	public List<EventEntity> getEventsForGuest(@Param(value = "guest") UserEntity guest); 

	public List<EventEntity> findByGuest(UserEntity guest); //TODO: converter; 

}
