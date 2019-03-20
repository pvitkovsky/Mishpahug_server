package application.models.city;

import application.exceptions.ExceptionMishpaha;
import application.entities.CityEntity;
import application.entities.CountryEntity;

import java.util.List;

public interface ICityModel {
    public CityEntity getById(Integer id) throws ExceptionMishpaha;
    public CityEntity add(CityEntity data) throws ExceptionMishpaha;
    public CityEntity remove(Integer id) throws ExceptionMishpaha;
    public List<CityEntity> getAll() throws ExceptionMishpaha;
    public CityEntity updateName(Integer id, String name) throws ExceptionMishpaha;
    public CityEntity getByFullName(String name) throws ExceptionMishpaha;
    public CountryEntity getCountryByCity(Integer countryId) throws ExceptionMishpaha;
    public List<CityEntity> getByName(String name) throws ExceptionMishpaha;
    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity) throws ExceptionMishpaha;
}
