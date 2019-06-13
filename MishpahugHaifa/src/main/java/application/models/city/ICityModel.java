package application.models.city;

import application.entities.properties.CityEntity;
import application.entities.properties.CountryEntity;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ICityModel {
    public CityEntity getById(Integer id);

    public CityEntity add(CityEntity data);

    Iterable<CityEntity> getAll(Predicate predicate);

    public CityEntity deleteByID(Integer id);

    void deleteByName(String name);

    public void deleteAll();

    public List<CityEntity> getAll();

    public CityEntity updateName(Integer id, String name);

    public CountryEntity getCountryByCity(Integer countryId);

    public CityEntity getByName(String name);

    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity);
}
