package Application.repo;

import Application.entities.CityItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityItem, Integer> {
}
