package Application.repo;

import Application.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    public List<EventEntity> searchByFilter(HashMap<String, String> filter);
}
