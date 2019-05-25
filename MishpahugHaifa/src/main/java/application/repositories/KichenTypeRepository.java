package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.KitchenTypeEntity;

public interface KichenTypeRepository extends JpaRepository<KitchenTypeEntity, Integer> {
    public KitchenTypeEntity getByName(String name);
}

