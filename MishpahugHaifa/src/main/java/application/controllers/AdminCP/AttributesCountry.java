package application.controllers.AdminCP;

import application.entities.CountryEntity;
import application.models.country.ICountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/administrator/attributes/country")
public class AttributesCountry {
    @Autowired
    ICountryModel countryModel;

    @GetMapping(value="/getall")
    public List<CountryEntity> getAll(){
        return countryModel.getAll();

    }
    @GetMapping(value="/getbyname")
    public List<CountryEntity> getByName(@RequestParam(value = "name") String name){
        return countryModel.getByName(name);

    }

    @GetMapping(value="/getbyfullname")
    public CountryEntity getByFulName(@RequestParam(value = "name") String name){
        return countryModel.getByFullName(name);

    }
}
