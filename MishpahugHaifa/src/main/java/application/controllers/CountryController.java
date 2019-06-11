package application.controllers;

import application.controllers.interfaces.ICountryController;
import application.entities.CountryEntity;
import application.models.country.ICountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController implements ICountryController {
    @Autowired
    ICountryModel countryModel;

    @Override
    @PostMapping(value = "/")
    public void post(
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request, @RequestBody CountryEntity data){
        countryModel.addCountry(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request, @RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name){
        
        
        countryModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        
        
        countryModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request, @PathVariable(name = "name") String name){
        
        
        countryModel.deleteByName(name);
    }

    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        
        
        List<String> res = new ArrayList<>();
        List<CountryEntity> countryEntityList = countryModel.getAll();
        countryEntityList.forEach(item->res.add(item.getName()));
        return res;
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(
                      @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request, @PathVariable(name = "id") Integer id){
        
        
        return countryModel.getById(id).getName();
    }

}
