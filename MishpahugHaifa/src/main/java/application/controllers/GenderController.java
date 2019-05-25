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

import application.controllers.interfaces.IGenderController;
import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.gender.IGenderModel;
import application.utils.IConverter;
import lombok.extern.slf4j.Slf4j;

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
    public List<String> get() {
        return IConverter.PropertyToStringList(genderModel.getAll());
    }

    @Override
    @GetMapping(value = "/name/{name}")
    public GenderEntity get(@PathVariable(name = "name") String name) {
        return genderModel.getByName(name);

    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id") Integer id) {
        return genderModel.getById(id).getName();

    }

}
