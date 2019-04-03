package application.repositories;

import application.entities.KitchenTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KichenTypeRepository extends JpaRepository<KitchenTypeEntity, Integer> {
    public KitchenTypeEntity getByName(String name);
}

