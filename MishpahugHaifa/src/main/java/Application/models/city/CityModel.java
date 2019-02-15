package Application.models.city;

import Application.entities.CityItem;
import Application.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CityModel implements ICityModel {
    @Autowired
    CityRepository cityRepository;

    @Override
    public CityItem getById(Integer id) {
        return null;
    }

    @Override
    public CityItem addCity(CityItem data) {
        return null;
    }

    @Override
    public CityItem removeCity(Integer id) {
        return null;
    }

    @Override
    public CityItem updateName(Integer id, String name) {
        return null;
    }

    @Override
    public CityItem getByName(String name) {
        return cityRepository.getByName(name);
    }
}
