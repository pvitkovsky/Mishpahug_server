package application.models.city;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import application.exceptions.EntityExistsException;
import application.exceptions.NotFoundEntityException;
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
        if (!cityRepository.existsById(id)) throw new NotFoundEntityException("");
            return cityRepository.getOne(id);
    }

    @Override
    public CityEntity add(CityEntity data){
            return cityRepository.saveAndFlush(data);
    }

    @Override
    public Iterable<CityEntity> getAll(Predicate predicate) {
        return cityRepository.findAll(predicate);
    }

    @Override
    public CityEntity deleteByID(Integer id){
        if (!cityRepository.existsById(id)) throw new NotFoundEntityException("");
            CityEntity cityEntity = cityRepository.getOne(id);
            cityRepository.deleteById(id);
            return cityEntity;
    }

@Override
    public void deleteByName(String name){
        if (!cityRepository.existsByName(name)) throw new NotFoundEntityException("");
            cityRepository.deleteByName(name);
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
        if (!cityRepository.existsById(id)) throw new NotFoundEntityException("");
        if (cityRepository.existsByName(name)) throw new EntityExistsException("");
            CityEntity cityEntity = getById(id);
            cityEntity.setName(name);
            return cityRepository.saveAndFlush(cityEntity);
    }

    @Override
    public CountryEntity getCountryByCity(Integer id){
        if (!cityRepository.existsById(id)) throw new NotFoundEntityException("");
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
            if (!cityRepository.existsByName(z))
            {
                CityEntity city = new CityEntity();
                city.setName(z);
                countryEntity.addCity(city);
                cityRepository.save(city);
                result.add(city);
            }
        }
        return result;
    }
}
