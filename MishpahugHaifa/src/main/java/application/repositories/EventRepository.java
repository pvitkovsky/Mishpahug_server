package application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import application.entities.EventEntity;
import application.repositories.custom.EventRepositoryCustom;

public interface EventRepository extends JpaRepository<EventEntity, Integer>, EventRepositoryCustom {
  
	@Query("SELECT e from EventEntity e WHERE e.nameOfEvent = :name")
    public EventEntity byFullName(String name);

	
}
