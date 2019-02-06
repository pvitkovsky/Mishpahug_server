package Application.models.city;

import Application.entities.CityItem;

public interface ICityModel {
    public CityItem getById(Integer id);
    public CityItem addCity(CityItem data);
    public CityItem removeCity(Integer id);
    public CityItem updateName(Integer id, String name);
}
