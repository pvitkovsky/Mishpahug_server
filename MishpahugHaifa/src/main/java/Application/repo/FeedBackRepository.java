package application.repo;

import application.entities.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBackEntity, Integer> {
}
