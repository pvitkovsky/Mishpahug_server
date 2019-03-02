package Application.models.city;

import Application.Exeption.ExeptionMishpaha;
import Application.entities.CityEntity;
import Application.entities.CountryEntity;

import java.util.List;

public interface ICityModel {
    public CityEntity getById(Integer id) throws ExeptionMishpaha;
    public CityEntity add(CityEntity data) throws ExeptionMishpaha;
    public CityEntity remove(Integer id) throws ExeptionMishpaha;
    public List<CityEntity> getAll() throws ExeptionMishpaha;
    public CityEntity updateName(Integer id, String name) throws ExeptionMishpaha;
    public CityEntity getByFullName(String name) throws ExeptionMishpaha;
    public CountryEntity getCountryByCity(Integer countryId) throws ExeptionMishpaha;
    public List<CityEntity> getByName(String name) throws ExeptionMishpaha;
    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity) throws ExeptionMishpaha;
}
