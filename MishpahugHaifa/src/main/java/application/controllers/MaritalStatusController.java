package application.controllers;

import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.marriagestatus.IMaritalStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/meritalstatus")
public class MaritalStatusController {

    @Autowired
    IMaritalStatusModel maritalStatusModel;

    @GetMapping(value="/")
    public List<MaritalStatusEntity> get(){
        return maritalStatusModel.getAll();
    }

    @GetMapping(value="/{id}")
    public MaritalStatusEntity get(@PathVariable(name = "id") Integer id){
        System.out.println(maritalStatusModel.getById(id));
        return maritalStatusModel.getById(id);
    }

    @PostMapping(value="/")
    public void post(@RequestBody MaritalStatusEntity data) throws ExceptionMishpaha {
        maritalStatusModel.add(data);
    }

    @PutMapping(value="/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        maritalStatusModel.updateName(id, name);
    }

    @DeleteMapping(value="/")
    public void delete() throws ExceptionMishpaha {
        maritalStatusModel.deleteAll();
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        maritalStatusModel.deleteByID(id);
    }
}
