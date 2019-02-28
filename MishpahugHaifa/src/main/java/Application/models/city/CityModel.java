package Application.models.city;

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
    public CityEntity getById(Integer id) {
        return null;
    }

    @Override
    public CityEntity add(CityEntity data) {
        return null;
    }

    @Override
    public CityEntity remove(Integer id) {
        return null;
    }

    @Override
    public List<CityEntity> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public CityEntity updateName(Integer id, String name) {
        return null;
    }

    @Override
    public CityEntity getByFullName(String name) {
        return cityRepository.getByFullName(name);
    }

    @Override
    public List<CityEntity> getByCountry(Integer countryId) {
        return null;
    }

    @Override
    public List<CityEntity> getByName(String name) {
        return cityRepository.getByName(name);
    }

    @Override
    public List<CityEntity> addFromList(List<String> data, CountryEntity countryEntity) {
        List<CityEntity> result = new ArrayList<>();
        for(String z:data){
            CityEntity temp = new CityEntity();
            temp.setName(z);
            temp.setCountryEntity(countryEntity);
            cityRepository.save(temp);
            result.add(temp);
        }
        return result;
    }
}
