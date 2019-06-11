package application.repositories;

import application.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    public CountryEntity getByName(String name);
    public void deleteByName(String name);
}
