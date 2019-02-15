package Application.repo;

import Application.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    public CountryEntity getByName(String name);
}
