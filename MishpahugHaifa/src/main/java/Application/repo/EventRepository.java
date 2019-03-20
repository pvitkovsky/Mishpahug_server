package application.repo;

import application.entities.EventEntity;
import application.repo.custom.EventRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<EventEntity, Integer>, EventRepositoryCustom {
    @Query("SELECT e from EventEntity e WHERE e.nameOfEvent = :name")
    public EventEntity byFullName(String name);
}
