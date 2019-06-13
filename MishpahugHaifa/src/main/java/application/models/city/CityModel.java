package application.models.city;

import application.entities.properties.CityEntity;
import application.entities.properties.CountryEntity;
import application.repositories.CityRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityModel implements ICityModel {
    @Autowired
    CityRepository cityRepository;

    @Override
    public CityEntity getById(Integer id){
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
            CityEntity cityEntity = cityRepository.getOne(id);
            cityRepository.deleteById(id);
            return cityEntity;
    }

@Override
    public void deleteByName(String name){
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
