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

import application.controllers.interfaces.IKichenTypeController;
import application.entities.KitchenTypeEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.kichentype.IKichenTypeModel;
import application.utils.IConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/kichentype")
public class KichenTypeController implements IKichenTypeController {
    @Autowired
    IKichenTypeModel kichenTypeModel;
    
    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody KitchenTypeEntity data) throws ExceptionMishpaha {
        kichenTypeModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) {
        kichenTypeModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() {
        kichenTypeModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        kichenTypeModel.deleteByID(id);
    }


    @Override
    @GetMapping(value = "/")
    public List<String> get() {
        return IConverter.PropertyToStringList(kichenTypeModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id", required = false) Integer id) {
        return kichenTypeModel.getById(id).getName();
    }

}
