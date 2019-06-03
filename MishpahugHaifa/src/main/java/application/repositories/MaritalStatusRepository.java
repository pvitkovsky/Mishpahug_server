package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.MaritalStatusEntity;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Integer> {
    public MaritalStatusEntity getByName(String name);
    public void deleteByName(String name);
}
