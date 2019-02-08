package Application.repo;

import Application.entities.CountryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryItem, Integer> {
}
