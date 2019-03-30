package application.controllers;

import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.city.ICityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class CityController {
    @Autowired
    ICityModel cityModel;
    @PostMapping(value="/")
    public void post(@RequestBody CityEntity data) throws ExceptionMishpaha {
        cityModel.add(data);
    }

    @PutMapping(value="/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        cityModel.updateName(id, name);
    }

    @DeleteMapping(value="/")
    public void delete(@RequestParam(name = "id", required = false) Integer id) throws ExceptionMishpaha {
        if (id == null){
            cityModel.deleteAll();
        }
        else{
            cityModel.deleteByID(id);
        }
    }

    @GetMapping(value="/")
    public List<CityEntity> get(@RequestParam(name = "id", required = false) Integer id,
                                   @RequestBody(required = false) String data) throws ExceptionMishpaha {
        List<CityEntity> res = new ArrayList<>();
        if (id != null && data == null){
            res.add(cityModel.getById(id));
        }
        if (id == null && data != null){
            res.add(cityModel.getByName(data));
        }
        if (id == null && data == null){
            res = cityModel.getAll();
        }
        return res;
    }
}
