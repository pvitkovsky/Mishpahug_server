package Application.repo;

import Application.entities.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserItem, Integer> {}
