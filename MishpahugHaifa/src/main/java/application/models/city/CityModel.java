package application.models.city;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import application.exceptions.EntityExistsDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;
import application.entities.CountryEntity;
import application.repositories.CityRepository;

@Service
@Transactional
public class CityModel implements ICityModel {
    @Autowired
    CityRepository cityRepository;

    @Override
    public CityEntity getById(Integer id){
        try {
            return cityRepository.getOne(id);
        } catch (Exception e) {
            throw new EntityExistsDException("Error");
        }
    }

    @Override
    public CityEntity add(CityEntity data){
        try {
            return cityRepository.saveAndFlush(data);
        } catch (Exception e) {
            throw new EntityExistsDException("Error");
        }
    }

    @Override
    public Iterable<CityEntity> getAll(Predicate predicate) {
        return cityRepository.findAll(predicate);
    }

    @Override
    public CityEntity deleteByID(Integer id){
        try {
            CityEntity cityEntity = cityRepository.getOne(id);
            cityRepository.deleteById(id);
            return cityEntity;
        } catch (Exception e) {
            throw new EntityExistsDException("Error");
        }
    }

@Override
    public void deleteByName(String name){
        try {
            cityRepository.deleteByName(name);
        } catch (Exception e) {
            throw new EntityExistsDException("Error");
        }
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    @Override
    public List<CityEntity> getAll(){
            return cityRepository.findAll();
    }

    @Override
    public CityEntity updateName(Integer id, String name){
            CityEntity cityEntity = getById(id);
            cityEntity.setName(name);
            return cityRepository.saveAndFlush(cityEntity);
    }

    @Override
    public CountryEntity getCountryByCity(Integer id){
            return cityRepository.getOne(id).getCountryEntity();
    }

    @Override
    public CityEntity getByName(String name){
            return cityRepository.getByName(name);
    }

    @Override
    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity){
            List<CityEntity> result = new ArrayList<>();
            for (String z : data) {
                CityEntity city = new CityEntity();
                city.setName(z);
                countryEntity.addCity(city);
                cityRepository.save(city);
                result.add(city);
            }
            return result;
    }
}
