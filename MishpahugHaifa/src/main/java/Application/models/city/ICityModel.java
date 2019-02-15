package Application.models.city;

import Application.entities.CityEntity;

import java.util.List;

public interface ICityModel {
    public CityEntity getById(Integer id);
    public CityEntity add(CityEntity data);
    public CityEntity remove(Integer id);
    public CityEntity updateName(Integer id, String name);
    public CityEntity getByName(String name);
    public List<CityEntity> getByCountry(Integer countryId);
}
