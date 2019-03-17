package Application.repo;

import java.util.Set;

import Application.repo.custom.EventGuestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;


public interface EventGuestRepository extends JpaRepository<EventGuestRelation, Long>, EventGuestRepositoryCustom {
    

	public EventGuestRelation findByUserGuestAndEvent(UserEntity user, EventEntity event);
	
	
	public boolean removeByUserGuest(UserEntity user);
    public boolean removeByEvent(EventEntity event);
    public boolean removeByUserGuestAndEvent(UserEntity user, EventEntity event);
}
