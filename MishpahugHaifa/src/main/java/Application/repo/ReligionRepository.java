package Application.repo;

import Application.entities.ReligionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReligionRepository extends JpaRepository<ReligionEntity, Integer> {
    public ReligionEntity getByName(String name);
}
