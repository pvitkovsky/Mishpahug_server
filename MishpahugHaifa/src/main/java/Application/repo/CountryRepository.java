package Application.repo;

import Application.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    @Query("SELECT c from CountryEntity c WHERE c.name = :name")
    public CountryEntity getByFullName(String name);
    @Query("SELECT c from CountryEntity c WHERE c.name like '%:name%'")
    public List<CountryEntity> getByName(String name);
}
