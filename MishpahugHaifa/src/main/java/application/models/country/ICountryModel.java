package application.models.country;

import java.util.List;

import application.entities.CountryEntity;

public interface ICountryModel {
    public CountryEntity getById(Integer id);

    public CountryEntity addCountry(CountryEntity data);

    public void deleteByID(Integer id);

    public void deleteAll();

    public CountryEntity updateName(Integer id, String name);

    public List<CountryEntity> getAll();

    public CountryEntity getByName(String name);
}
