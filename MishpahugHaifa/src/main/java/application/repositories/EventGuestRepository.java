package application.repositories;

import application.entities.EventEntity;
import application.entities.EventGuestRelation;
import application.entities.EventGuestRelation.EventGuestId;
import application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventGuestRepository extends JpaRepository<EventGuestRelation, EventGuestId> {


	@Query("SELECT r.userGuest FROM EventGuestRelation r WHERE r.event = :event")    
	public List<UserEntity> getGuestsForEvent (@Param(value = "event") EventEntity event); 
	
	public List<UserEntity> findByEvent(EventEntity event); //TODO: converter; 


	@Query("SELECT r.event FROM EventGuestRelation r WHERE r.userGuest = :guest")    
	public List<EventEntity> getEventsForGuest(@Param(value = "guest") UserEntity guest); 

	public List<EventEntity> findByUserGuest(UserEntity guest); //TODO: converter; 

}
