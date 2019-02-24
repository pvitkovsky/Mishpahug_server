package Application.models.country;

import Application.entities.CountryEntity;
import Application.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
    public CountryEntity getByFullName(String name) {
        return countryRepository.getByFullName(name);
    }

    @Override
    public List<CountryEntity> getByName(String name) {
        return countryRepository.getByName(name);
    }

    @Override
    public List<CountryEntity> getAll() {
        return null;
    }
}
