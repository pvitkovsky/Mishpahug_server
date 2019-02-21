package Application.repo;

import Application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //public List<UserEntity> searchByFilter(HashMap<String, String> filter);
}
