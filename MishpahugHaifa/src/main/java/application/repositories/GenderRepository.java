package application.repositories;

import application.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {
    public GenderEntity getByFullName(String name);
}
