package application.repositories;

import application.entities.MaritalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Integer> {
    @Query("SELECT m from MaritalStatusEntity m WHERE m.name = :name")
    public MaritalStatusEntity getByName(String name);
}
