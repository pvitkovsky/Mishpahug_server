package application.models.country;

import application.entities.CountryEntity;

import java.util.List;

public interface ICountryModel {
    public CountryEntity getById(Integer id);
    public CountryEntity addCountry(CountryEntity data);
    public void removeCountry(Integer id);
    public CountryEntity updateName(Integer id, String name);
    public CountryEntity getByFullName(String name);
    public List<CountryEntity> getAll();
    public List<CountryEntity> getByName(String name);
}
