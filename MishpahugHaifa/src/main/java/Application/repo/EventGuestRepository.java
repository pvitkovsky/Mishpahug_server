package Application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.EventGuestRelation.EventGuestId;
import Application.entities.UserEntity;


public interface EventGuestRepository extends JpaRepository<EventGuestRelation, EventGuestId> {
    

	public EventGuestRelation findByUserGuestAndEvent(UserEntity user, EventEntity event);
	
	@Query("SELECT r.event FROM EventGuestRelation r WHERE r.userGuest = :guest")
	public List<EventEntity> getEventIdsForGuest(@Param(value = "guest") UserEntity guest); 
	
//	@Query
//	public Iterable<Integer> getUserIdsForEvent(EventEntity event); 
	
	
	public boolean removeByUserGuest(UserEntity user);
    public boolean removeByEvent(EventEntity event);
    public boolean removeByUserGuestAndEvent(UserEntity user, EventEntity event);
    
//	@Query("SELECT r.event FROM EventGuestRelation r, EventEntity e WHERE r.event = e AND r.userGuest = :guest") didnt work

}
