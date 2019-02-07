package Application.models.country;

import Application.entities.CountryItem;

public interface ICountryModel {
    public CountryItem getById(Integer id);
    public CountryItem addCountry(CountryItem data);
    public CountryItem removeCountry(Integer id);
    public CountryItem updateName(Integer id, String name);
}
