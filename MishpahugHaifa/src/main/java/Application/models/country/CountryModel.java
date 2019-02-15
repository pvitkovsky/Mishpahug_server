package Application.models.country;

import Application.entities.CountryItem;
import Application.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryModel implements ICountryModel {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public CountryItem getById(Integer id) {
        return null;
    }

    @Override
    public CountryItem addCountry(CountryItem data) {
        return null;
    }

    @Override
    public CountryItem removeCountry(Integer id) {
        return null;
    }

    @Override
    public CountryItem updateName(Integer id, String name) {
        return null;
    }

    @Override
    public CountryItem getByName(String name) {
        return countryRepository.getByName(name);
    }
}
