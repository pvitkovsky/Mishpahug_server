package application.controllers;

import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.gender.IGenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/gender")
public class GenderController {
    @Autowired
    IGenderModel genderModel;

    @PostMapping(value="/")
    public void post(@RequestBody GenderEntity data) throws ExceptionMishpaha {
        genderModel.add(data);
    }

    @PutMapping(value="/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        genderModel.updateName(id, name);
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        genderModel.deleteByID(id);
    }

    @DeleteMapping(value="/")
    public void delete() throws ExceptionMishpaha {
        genderModel.deleteAll();
    }

    @GetMapping(value="/")
    public List<GenderEntity> get() throws ExceptionMishpaha {
            return genderModel.getAll();
    }

    @GetMapping(value="/{id}")
    public GenderEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        return genderModel.getById(id);
    }

    @GetMapping(value="/{name}")
    public GenderEntity get(@RequestBody String data) throws ExceptionMishpaha {
        return genderModel.getByName(data);
    }
}
