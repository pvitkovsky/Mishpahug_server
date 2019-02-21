package Application.repo;

import Application.entities.EventEntity;
import Application.repo.custom.EventRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer>, EventRepositoryCustom {

}
