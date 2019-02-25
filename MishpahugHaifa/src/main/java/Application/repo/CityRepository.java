package Application.repo;

import Application.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
        @Query("SELECT c from CityEntity c WHERE c.name = :name")
        public CityEntity getByFullName(String name);
        @Query("SELECT c from CityEntity c WHERE c.name like %:name%")
        public List<CityEntity> getByName(String name);

}
