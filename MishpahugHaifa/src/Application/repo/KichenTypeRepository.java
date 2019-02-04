package Application.repo;

import Application.entities.KichenTypeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KichenTypeRepository extends JpaRepository<KichenTypeItem, Integer> {}
