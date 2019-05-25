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

import application.controllers.interfaces.IMaritalStatusController;
import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.marriagestatus.IMaritalStatusModel;
import application.utils.IConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/meritalstatus")
public class MaritalStatusController implements IMaritalStatusController {

    @Autowired
    IMaritalStatusModel maritalStatusModel;
    
    @Override
    @GetMapping(value = "/")
    public List<String> get() {
        return IConverter.PropertyToStringList(maritalStatusModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public MaritalStatusEntity get(@PathVariable(name = "id") Integer id) {
        System.out.println(maritalStatusModel.getById(id));
        return maritalStatusModel.getById(id);
    }

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody MaritalStatusEntity data) throws ExceptionMishpaha {
        maritalStatusModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        maritalStatusModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() throws ExceptionMishpaha {
        maritalStatusModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        maritalStatusModel.deleteByID(id);
    }
}
