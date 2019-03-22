package application.repositories;

import application.entities.KichenTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KichenTypeRepository extends JpaRepository<KichenTypeEntity, Integer> {
    @Query("SELECT kt from KichenTypeEntity kt WHERE kt.name like %:name%")
    public List<KichenTypeEntity> getByName(String name);
    @Query("SELECT kt from KichenTypeEntity kt WHERE kt.name = :name")
    public List<KichenTypeEntity> getByFullName(String name);
}

