package application.controllers;

import application.entities.CountryEntity;
import application.models.country.ICountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {
    @Autowired
    ICountryModel countryModel;

    @PostMapping(value="/")
    public void post(@RequestBody CountryEntity data){
        countryModel.addCountry(data);
    }

    @PutMapping(value="/")
    public void post(@RequestParam(name = "id") Integer id,
                     @RequestParam(name = "name") String name){
        countryModel.updateName(id, name);
    }

    @DeleteMapping(value="/")
    public void delete(){
        countryModel.deleteAll();
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id){
        countryModel.deleteByID(id);
    }

    @GetMapping(value="/")
    public List<CountryEntity> get(){
        return countryModel.getAll();
    }

    @GetMapping(value="/{id}")
    public CountryEntity get(@PathVariable(name = "id") Integer id){
        return countryModel.getById(id);
    }

}
