package application.controllers;

import application.controllers.intarfaces.IGenderController;
import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.gender.IGenderModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/gender")
public class GenderController implements IGenderController {
    @Autowired
    IGenderModel genderModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody GenderEntity data) throws ExceptionMishpaha {
        genderModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        genderModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        genderModel.deleteByID(id);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() {
        genderModel.deleteAll();

    }

    @Override
    @GetMapping(value = "/")
    public List<GenderEntity> get() {
        return genderModel.getAll();

    }

    @Override
    @GetMapping(value = "/name/{name}")
    public GenderEntity get(@PathVariable(name = "name") String name) {
        return genderModel.getByName(name);

    }

    @Override
    @GetMapping(value = "/{id}")
    public GenderEntity get(@PathVariable(name = "id") Integer id) {
        return genderModel.getById(id);

    }

}
