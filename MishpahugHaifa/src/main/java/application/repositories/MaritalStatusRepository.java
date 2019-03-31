package application.repositories;

import application.entities.MaritalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Integer> {
    public MaritalStatusEntity getByName(String name);
}
