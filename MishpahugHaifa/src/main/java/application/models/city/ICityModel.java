package application.models.city;

import java.util.List;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;
import application.entities.CountryEntity;

public interface ICityModel {
    public CityEntity getById(Integer id);

    public CityEntity add(CityEntity data);

    Iterable<CityEntity> getAll(Predicate predicate);

    public CityEntity deleteByID(Integer id);

    void deleteByName(String name);

    public void deleteAll();

    public CityEntity updateName(Integer id, String name);

    public CountryEntity getCountryByCity(Integer countryId);

    public CityEntity getByName(String name);

    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity);
}
