package application.repositories;

import application.entities.KitchenTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KichenTypeRepository extends JpaRepository<KitchenTypeEntity, Integer> {
    @Query("SELECT kt from KitchenTypeEntity kt WHERE kt.name = :name")
    public KitchenTypeEntity getByName(String name);
}

