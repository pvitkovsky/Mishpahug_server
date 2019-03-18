package Application.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Application.entities.EventEntity;
import Application.entities.UserEntity;
import Application.repo.custom.EventRepositoryCustom;

public interface EventRepository extends JpaRepository<EventEntity, Integer>, EventRepositoryCustom {
  
	@Query("SELECT e from EventEntity e WHERE e.nameOfEvent = :name")
    public EventEntity byFullName(String name);
    
    public Set<EventEntity> findAllBySubscriptions(UserEntity user); //TODO: properly working query pls;

}
