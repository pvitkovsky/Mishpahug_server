package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    public CountryEntity getByName(String name);
    public void deleteByName(String name);
}
