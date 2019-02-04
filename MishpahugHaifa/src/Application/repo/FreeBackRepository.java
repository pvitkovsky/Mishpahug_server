package Application.repo;

import Application.entities.FreeBackItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBackRepository extends JpaRepository<FreeBackItem, Integer> {}
