package application.repositories;

import application.entities.properties.MaritalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Integer> {
    public MaritalStatusEntity getByName(String name);
    public void deleteByName(String name);
    public Boolean existsByName(String name);
}
