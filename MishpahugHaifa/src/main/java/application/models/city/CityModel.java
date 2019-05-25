package application.models.city;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;
import application.entities.CountryEntity;
import application.exceptions.ExceptionMishpaha;
import application.repositories.CityRepository;

@Service
@Transactional
public class CityModel implements ICityModel {
    @Autowired
    CityRepository cityRepository;

    @Override
    public CityEntity getById(Integer id) throws ExceptionMishpaha {
        try {
            return cityRepository.getOne(id);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CityEntity add(CityEntity data) throws ExceptionMishpaha {
        try {
            return cityRepository.saveAndFlush(data);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public Iterable<CityEntity> getAll(Predicate predicate) {
        return cityRepository.findAll(predicate);
    }

    @Override
    public CityEntity deleteByID(Integer id) throws ExceptionMishpaha {
        try {
            CityEntity cityEntity = cityRepository.getOne(id);
            cityRepository.deleteById(id);
            return cityEntity;
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    @Override
    public List<CityEntity> getAll() throws ExceptionMishpaha {
        try {
            return cityRepository.findAll();
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CityEntity updateName(Integer id, String name) throws ExceptionMishpaha {
        try {
            CityEntity cityEntity = getById(id);
            cityEntity.setName(name);
            return cityRepository.saveAndFlush(cityEntity);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CountryEntity getCountryByCity(Integer id) throws ExceptionMishpaha {
        try {
            return cityRepository.getOne(id).getCountryEntity();
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CityEntity getByName(String name) throws ExceptionMishpaha {
        try {
            return cityRepository.getByName(name);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity) throws ExceptionMishpaha {
        try {
            List<CityEntity> result = new ArrayList<>();
            for (String z : data) {
                CityEntity city = new CityEntity();
                city.setName(z);
                countryEntity.addCity(city);
                cityRepository.save(city);
                result.add(city);

            }
            return result;
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }
}
