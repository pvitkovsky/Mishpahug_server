package Application.controllers;

import Application.exceptions.ExceptionMishpaha;
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
