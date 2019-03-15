package Application.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;

public interface EvengGuestRepository extends JpaRepository<EventGuestRelation, Integer> {
		public Set<EventGuestRelation> findAllByUser(UserEntity user);
		public Set<EventGuestRelation> findAllByEvent(EventEntity Event);
}
