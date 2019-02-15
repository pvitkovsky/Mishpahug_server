package Application.models.country;

import Application.entities.CountryEntity;

import java.util.List;

public interface ICountryModel {
    public CountryEntity getById(Integer id);
    public CountryEntity addCountry(CountryEntity data);
    public CountryEntity removeCountry(Integer id);
    public CountryEntity updateName(Integer id, String name);
    public CountryEntity getByName(String name);
    public List<CountryEntity> getAll();
}
