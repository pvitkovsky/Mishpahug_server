package Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Application.entities.EventGuestRelation;
import Application.entities.EventGuestRelation.EventGuestId;

public interface EventGuestRepository extends JpaRepository<EventGuestRelation, EventGuestId> {

	// TODO: working queries please
	// public EventGuestRelation findByUserGuestAndEvent(UserEntity user,
	// EventEntity event);
	// public boolean removeByUserGuestAndEvent(UserEntity user, EventEntity event);

	// @Query("SELECT r.event FROM EventGuestRelation r WHERE r.userGuest = :guest")
	// //TODO: join doesn't work as intended
	// public List<EventEntity> getEventIdsForGuest(@Param(value = "guest")
	// UserEntity guest);
	// public List<EventGuestRelation> findByUserGuest(UserEntity guest); //TODO:
	// join doesn't work as intended
	// public boolean removeByUserGuest(UserEntity user);

	// @Query("SELECT r.event FROM EventGuestRelation r WHERE r.event= :event")
	// //TODO: join doesn't work as intended
	// public List<EventEntity> getEventIdsForEvent(@Param(value = "event")
	// EventEntity event);
	// public List<EventGuestRelation> findByEvent(EventEntity event);
	// public boolean removeByEvent(EventEntity event);

}
