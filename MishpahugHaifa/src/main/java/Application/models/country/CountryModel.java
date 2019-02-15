package Application.models.country;

import Application.entities.CountryEntity;
import Application.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CountryModel implements ICountryModel {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public CountryEntity getById(Integer id) {
        return null;
    }

    @Override
    public CountryEntity addCountry(CountryEntity data) {
        return null;
    }

    @Override
    public CountryEntity removeCountry(Integer id) {
        return null;
    }

    @Override
    public CountryEntity updateName(Integer id, String name) {
        return null;
    }

    @Override
    public CountryEntity getByName(String name) {
        return countryRepository.getByName(name);
    }

    @Override
    public List<CountryEntity> getAll() {
        return null;
    }
}
