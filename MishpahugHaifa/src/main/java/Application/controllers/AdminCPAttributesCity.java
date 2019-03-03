package Application.controllers;

import Application.entities.CityEntity;
import Application.entities.CountryEntity;
import Application.models.city.ICityModel;
import Application.models.country.ICountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/administrator/attributes/city")
public class AdminCPAttributesCity {
    @Autowired
    ICityModel cityModel;
    @Autowired
    ICountryModel countryModel;
    @GetMapping(value="/getall")
    public List<CityEntity> getAll(){
        return cityModel.getAll();
    }
    @GetMapping(value="/addtocountry")
    public List<CityEntity> addToCountry(@RequestBody List<String> data,
                                         @RequestParam(value = "country") String countryName){
        CountryEntity countryEntity = countryModel.getByFullName(countryName);
        if (countryEntity == null) {
            countryEntity = new CountryEntity();
            countryEntity.setName(countryName);
            countryModel.addCountry(countryEntity);
        }
        return cityModel.addFromList(data, countryEntity);
    }
}
