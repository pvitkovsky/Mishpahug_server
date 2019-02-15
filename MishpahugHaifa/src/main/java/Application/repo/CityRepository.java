package Application.repo;

import Application.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
        public CityEntity getByName(String name);
}
