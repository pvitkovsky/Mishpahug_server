package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.controllers.interfaces.ICountryController;
import application.entities.CountryEntity;
import application.models.country.ICountryModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/country")
public class CountryController implements ICountryController {
    @Autowired
    ICountryModel countryModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody CountryEntity data) {
        countryModel.addCountry(data);
    }

    @Override
    @PutMapping(value = "/")
    public void post(@RequestParam(name = "id") Integer id,
                     @RequestParam(name = "name") String name) {
        countryModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() {
        countryModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        countryModel.deleteByID(id);
    }

    @Override
    @GetMapping(value = "/")
    public List<CountryEntity> get() {
        return countryModel.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public CountryEntity get(@PathVariable(name = "id") Integer id) {
        return countryModel.getById(id);
    }

}
