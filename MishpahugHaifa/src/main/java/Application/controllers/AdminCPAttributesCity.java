package application.controllers;

import application.exceptions.ExceptionMishpaha;
import application.entities.CityEntity;
import application.entities.CountryEntity;
import application.models.city.ICityModel;
import application.models.country.ICountryModel;
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
        try {
            return cityModel.getAll();
        } catch (ExceptionMishpaha exeptionMishpaha) {
            exeptionMishpaha.printStackTrace();
            return null;
        }
    }
    @GetMapping(value="/addtocountry")
    public List<CityEntity> addToCountry(@RequestBody List<String> data,
                                         @RequestParam(value = "country") String countryName){
        CountryEntity countryEntity = countryModel.getByFullName(countryName);

        try {
            return cityModel.addFromList(data, countryEntity);
        } catch (ExceptionMishpaha exeptionMishpaha) {
            exeptionMishpaha.printStackTrace();
            return null;
        }
    }
}
