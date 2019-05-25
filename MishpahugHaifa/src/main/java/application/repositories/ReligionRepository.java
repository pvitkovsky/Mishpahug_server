package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.ReligionEntity;

public interface ReligionRepository extends JpaRepository<ReligionEntity, Integer> {
    public ReligionEntity getByName(String name);
}
