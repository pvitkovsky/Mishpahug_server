package Application.repo;

import Application.entities.UserRatingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingRepository extends JpaRepository<UserRatingItem, Integer> {}
