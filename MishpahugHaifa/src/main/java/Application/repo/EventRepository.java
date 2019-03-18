package Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Application.entities.EventEntity;
import Application.repo.custom.EventRepositoryCustom;

public interface EventRepository extends JpaRepository<EventEntity, Integer>, EventRepositoryCustom {
  
	@Query("SELECT e from EventEntity e WHERE e.nameOfEvent = :name")
    public EventEntity byFullName(String name);

//	@Query("SELECT e from EventEntity e WHERE :user member of e.subscriptions")	
//    public Set<EventEntity> findAllBySubscriptions(@Param(value = "user") UserEntity user); //TODO: properly working query pls;
/*
 * Trying to :subscription member of e.subscriptions failed due to multi-column query limitation in H2;
 * 
 */
}
