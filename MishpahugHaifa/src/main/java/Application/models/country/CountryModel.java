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
        return countryRepository.getOne(id);
    }

    @Override
    public CountryEntity addCountry(CountryEntity data) {
        return countryRepository.save(data);
    }

    @Override
    public void removeCountry(Integer id) {
        countryRepository.deleteById(id);
    }

    @Override
    public CountryEntity updateName(Integer id, String name) {
        CountryEntity countryEntity = countryRepository.getOne(id);
        countryEntity.setName(name);
        return countryRepository.save(countryEntity);
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
        return countryRepository.findAll();
    }
}
