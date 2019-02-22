package Application.repo;

import Application.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    public CountryEntity getByFullName(String name);
    public List<CountryEntity> getByName(String name);
}
