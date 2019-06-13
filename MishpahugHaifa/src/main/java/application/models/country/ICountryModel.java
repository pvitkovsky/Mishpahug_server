package application.models.country;

import application.entities.properties.CountryEntity;

import java.util.List;

public interface ICountryModel {
    public CountryEntity getById(Integer id);

    public CountryEntity addCountry(CountryEntity data);

    public void deleteByName(String name);

    public void deleteAll();

    public CountryEntity updateName(Integer id, String name);

    public List<CountryEntity> getAll();

    public CountryEntity getByName(String name);
}
