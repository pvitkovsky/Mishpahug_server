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
    public void delete() {
        genderModel.deleteAll();
    }

    @GetMapping(value="/")
    public List<GenderEntity> get() {
            return genderModel.getAll();
    }

    @GetMapping(value="/name/{name}")
    publiGenderEntity get(@PathVariable(name = "name") String name) {
        return genderModel.getByName(name);
    }

    @GetMapping(value="/{id}")
    public GenderEntity get(@PathVariable(name = "id") Integer id) {
        return genderModel.getById(id);
    }

}
