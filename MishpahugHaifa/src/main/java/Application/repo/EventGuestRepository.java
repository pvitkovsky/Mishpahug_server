package Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.EventGuestRelation.EventGuestId;
import Application.entities.UserEntity;
import Application.repo.custom.EventGuestRepositoryCustom;


public interface EventGuestRepository extends JpaRepository<EventGuestRelation, EventGuestId>, EventGuestRepositoryCustom {
    

	public EventGuestRelation findByUserGuestAndEvent(UserEntity user, EventEntity event);
	
	
	public boolean removeByUserGuest(UserEntity user);
    public boolean removeByEvent(EventEntity event);
    public boolean removeByUserGuestAndEvent(UserEntity user, EventEntity event);
}
