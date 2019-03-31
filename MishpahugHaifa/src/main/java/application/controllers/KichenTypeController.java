package application.controllers;

import application.entities.KitchenTypeEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.kichentype.IKichenTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/kichentype")
public class KichenTypeController {
    @Autowired
    IKichenTypeModel kichenTypeModel;

    @PostMapping(value="/")
    public void post(@RequestBody KitchenTypeEntity data) throws ExceptionMishpaha {
        kichenTypeModel.add(data);
    }

    @PutMapping(value="/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) {
        kichenTypeModel.updateName(id, name);
    }

    @DeleteMapping(value="/")
    public void delete() {
        kichenTypeModel.deleteAll();
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        kichenTypeModel.deleteByID(id);
    }


    @GetMapping(value="/")
    public List<KitchenTypeEntity> get() {
        List<KitchenTypeEntity> res = new ArrayList<>();
            return kichenTypeModel.getAll();
    }

    @GetMapping(value="/{id}")
    public KitchenTypeEntity get(@PathVariable(name = "id", required = false) Integer id) {
           return kichenTypeModel.getById(id);
    }

    @GetMapping(value="/{name}")
    public KitchenTypeEntity get(@RequestBody String data) {
           return kichenTypeModel.getByName(data);
    }


}
