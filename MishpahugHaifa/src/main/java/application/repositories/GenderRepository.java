package application.repositories;

import application.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {
    @Query("SELECT g from GenderEntity g WHERE g.name = :name")
    public GenderEntity findByName(String name);
}
