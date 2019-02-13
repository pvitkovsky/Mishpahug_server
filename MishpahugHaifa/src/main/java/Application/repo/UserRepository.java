package Application.repo;

import Application.entities.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface UserRepository extends JpaRepository<UserItem, Integer> {
    public List<UserItem> searchByFilter(HashMap<String, String> filter);
}
