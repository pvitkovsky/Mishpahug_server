package Application.repo;

import Application.entities.EventEntity;
import Application.repo.custom.EventRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer>, EventRepositoryCustom {
    @Query("SELECT e from EventEntity e WHERE e.nameOfEvent = :name")
    public EventEntity byFullName(String name);
}
