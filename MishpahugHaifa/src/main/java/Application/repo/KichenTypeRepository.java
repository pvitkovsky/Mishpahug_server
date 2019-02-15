package Application.repo;

import Application.entities.KichenTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KichenTypeRepository extends JpaRepository<KichenTypeEntity, Integer> {}
