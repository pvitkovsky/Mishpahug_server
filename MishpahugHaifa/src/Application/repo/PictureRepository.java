package Application.repo;

import Application.entities.PictureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<PictureItem, Integer> {
}
