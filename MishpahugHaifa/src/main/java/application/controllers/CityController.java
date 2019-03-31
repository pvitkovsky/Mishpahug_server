package application.controllers;

import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.city.ICityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
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
    public void delete() {
        cityModel.deleteAll();
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        cityModel.deleteByID(id);
    }

    @GetMapping(value="/")
    public List<CityEntity> get() throws ExceptionMishpaha {
        return cityModel.getAll();
    }

    @GetMapping(value="/{id}")
    public CityEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        return cityModel.getById(id);
    }

    @GetMapping(value="/{name}")
    public CityEntity get(@RequestBody String data) throws ExceptionMishpaha {
        return cityModel.getByName(data);
    }
}
