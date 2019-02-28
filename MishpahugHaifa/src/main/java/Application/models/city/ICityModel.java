package Application.models.city;

import Application.entities.CityEntity;
import Application.entities.CountryEntity;

import java.util.List;

public interface ICityModel {
    public CityEntity getById(Integer id);
    public CityEntity add(CityEntity data);
    public CityEntity remove(Integer id);
    public List<CityEntity> getAll();
    public CityEntity updateName(Integer id, String name);
    public CityEntity getByFullName(String name);
    public List<CityEntity> getByCountry(Integer countryId);
    public List<CityEntity> getByName(String name);
    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity);
}
