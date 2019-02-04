package Application.repo;

import Application.entities.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventItem, Integer> {}
