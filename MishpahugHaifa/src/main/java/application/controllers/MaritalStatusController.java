package application.controllers;

import application.controllers.interfaces.IMaritalStatusController;
import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.marriagestatus.IMaritalStatusModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/meritalstatus")
public class MaritalStatusController implements IMaritalStatusController {

    @Autowired
    IMaritalStatusModel maritalStatusModel;

    @Override
    @GetMapping(value = "/")
    public List<MaritalStatusEntity> get() {
        return maritalStatusModel.getAll();
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
