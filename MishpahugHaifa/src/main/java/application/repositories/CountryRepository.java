package application.repositories;

import application.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    @Query("SELECT c from CountryEntity c WHERE c.name = :name")
    public CountryEntity getByName(String name);
}
