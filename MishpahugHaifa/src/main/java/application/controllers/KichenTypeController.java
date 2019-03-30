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
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        kichenTypeModel.updateName(id, name);
    }

    @DeleteMapping(value="/")
    public void delete(@RequestParam(name = "id", required = false) Integer id) throws ExceptionMishpaha {
        if (id == null){
            kichenTypeModel.deleteAll();
        }
        else{
            kichenTypeModel.deleteByID(id);
        }
    }

    @GetMapping(value="/")
    public List<KitchenTypeEntity> get(@RequestParam(name = "id", required = false) Integer id,
                                  @RequestBody(required = false) String data) throws ExceptionMishpaha {
        List<KitchenTypeEntity> res = new ArrayList<>();
        if (id != null && data == null){
            res.add(kichenTypeModel.getById(id));
        }
        if (id == null && data != null){
            res.add(kichenTypeModel.getByName(data));
        }
        if (id == null && data == null){
            res = kichenTypeModel.getAll();
        }
        return res;
    }
}
