package Application.models.city;

import Application.Exeption.ExeptionMishpaha;
import Application.entities.CityEntity;
import Application.entities.CountryEntity;
import Application.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CityModel implements ICityModel {
    @Autowired
    CityRepository cityRepository;

    @Override
    public CityEntity getById(Integer id) throws ExeptionMishpaha {
        try {
            return cityRepository.getOne(id);
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CityEntity add(CityEntity data)  throws ExeptionMishpaha {
        try {
            return cityRepository.saveAndFlush(data);
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CityEntity remove(Integer id)  throws ExeptionMishpaha {
        try {
            CityEntity cityEntity = cityRepository.getOne(id);
            cityRepository.deleteById(id)
            return cityEntity;
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public List<CityEntity> getAll()  throws ExeptionMishpaha {
        try {
            return cityRepository.findAll();
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CityEntity updateName(Integer id, String name) throws ExeptionMishpaha {
        try {
            CityEntity cityEntity = getById(id);
            cityEntity.setName(name);
            return cityRepository.saveAndFlush(cityEntity);
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CityEntity getByFullName(String name) throws ExeptionMishpaha {
       try {
            return cityRepository.getByFullName(name);
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public CountryEntity getCountryByCity(Integer id) throws ExeptionMishpaha {
        try {
            return cityRepository.getOne(id).getCountryEntity();
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public List<CityEntity> getByName(String name) throws ExeptionMishpaha {
        try {
            return cityRepository.getByName(name);
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity) throws ExeptionMishpaha {
        try {
            CityEntity cityEntity = new CityEntity();
            List<CityEntity> result = new ArrayList<>();
            for(String z:data){
                CityEntity temp = new CityEntity();
                temp.setName(z);
                temp.setCountryEntity(countryEntity);
                CountryEntity countryEntity1 = new CountryEntity();
                cityRepository.save(temp);
                result.add(temp);

            }
            return result;
        } catch (Exception e) {
            throw new ExeptionMishpaha(this.getClass().toString(), e);
        }
    }
}
