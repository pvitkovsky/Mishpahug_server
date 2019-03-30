package application.controllers;

import application.entities.CountryEntity;
import application.models.country.ICountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public void delete(@RequestParam(name = "id", required = false) Integer id){
        if (id == null){
            countryModel.deleteAll();
        }
        else{
            countryModel.deleteByID(id);
        }
    }

    @GetMapping(value="/")
    public List<CountryEntity> get(@RequestParam(name = "id", required = false) Integer id,
                                   @RequestBody(required = false) String data){
        List<CountryEntity> res = new ArrayList<>();
        if (id != null && data == null){
            res.add(countryModel.getById(id));
        }
        if (id == null && data != null){
            res.add(countryModel.getByName(data));
        }
        if (id == null && data == null){
            res = countryModel.getAll();
        }
        return res;
    }
}
