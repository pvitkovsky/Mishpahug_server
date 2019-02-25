package Application.repo;

import Application.entities.ReligionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReligionRepository extends JpaRepository<ReligionEntity, Integer> {
    @Query("SELECT r from ReligionEntity r WHERE r.name = :name")
    public ReligionEntity getByFullName(String name);
    @Query("SELECT r from ReligionEntity r WHERE r.name like %:name%")
    public List<ReligionEntity> getByName(String name);
}
