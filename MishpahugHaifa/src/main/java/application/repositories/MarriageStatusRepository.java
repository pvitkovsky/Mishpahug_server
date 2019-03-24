package application.repositories;

import application.entities.MarriageStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarriageStatusRepository extends JpaRepository<MarriageStatusEntity, Integer> {
    @Query("SELECT m from MarriageStatusEntity m WHERE m.name = :name")
    public MarriageStatusEntity findByName(String name);
}
