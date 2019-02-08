package Application.repo;

import Application.entities.EventRatingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRatingRepository extends JpaRepository<EventRatingItem, Integer> {}
