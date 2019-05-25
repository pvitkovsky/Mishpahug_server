package application.controllers;

import application.controllers.interfaces.IKichenTypeController;
import application.entities.KitchenTypeEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.kichentype.IKichenTypeModel;
import application.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return Converter.kitchenTypestoStringList(kichenTypeModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id", required = false) Integer id) {
        return kichenTypeModel.getById(id).getName();
    }

}
