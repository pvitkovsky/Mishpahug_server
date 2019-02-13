package Application.repo;

import Application.entities.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface EventRepository extends JpaRepository<EventItem, Integer> {
    public List<EventItem> searchByFilter(HashMap<String, String> filter);
}
