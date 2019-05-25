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

import application.controllers.interfaces.IReligionController;
import application.entities.ReligionEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.religion.IReligionModel;
import application.utils.IConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/religion")
public class ReligionController implements IReligionController {

    @Autowired
    IReligionModel religionModel;
    
    @Override
    @GetMapping(value = "/")
    public List<String> get() {
        return IConverter.PropertyToStringList(religionModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ReligionEntity get(@PathVariable(name = "id") Integer id) {
        return religionModel.getById(id);

    }

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody ReligionEntity data) {
        religionModel.add(data);

    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        religionModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() {
        religionModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        religionModel.deleteByID(id);
    }

}
