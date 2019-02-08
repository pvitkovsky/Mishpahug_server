package Application.repo;

import Application.entities.ReligionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReligionRepository extends JpaRepository<ReligionItem, Integer> {}
