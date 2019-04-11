package application.models.country;

import application.entities.CountryEntity;

import java.util.List;

public interface ICountryModel {
    public CountryEntity getById(Integer id);

    public CountryEntity addCountry(CountryEntity data);

    public void deleteByID(Integer id);

    public void deleteAll();

    public CountryEntity updateName(Integer id, String name);

    public List<CountryEntity> getAll();

    public CountryEntity getByName(String name);
}
