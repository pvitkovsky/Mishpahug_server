package Application.repo;

import Application.entities.FeedBackItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBackItem, Integer> {}
