package application.controllers;

import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.gender.IGenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/gender")
public class GenderController {
    @Autowired
    IGenderModel genderModel;

    @PostMapping(value="/")
    public void post(@RequestBody GenderEntity data) throws ExceptionMishpaha {
        genderModel.add(data);
    }

    @PutMapping(value="/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        genderModel.updateName(id, name);
    }

    @DeleteMapping(value="/")
    public void delete(@RequestParam(name = "id", required = false) Integer id) throws ExceptionMishpaha {
        if (id == null){
            genderModel.deleteAll();
        }
        else{
            genderModel.deleteByID(id);
        }
    }

    @GetMapping(value="/")
    public List<GenderEntity> get(@RequestParam(name = "id", required = false) Integer id,
                                @RequestBody(required = false) String data) throws ExceptionMishpaha {
        List<GenderEntity> res = new ArrayList<>();
        if (id != null && data == null){
            res.add(genderModel.getById(id));
        }
        if (id == null && data != null){
            res.add(genderModel.getByName(data));
        }
        if (id == null && data == null){
            res = genderModel.getAll();
        }
        return res;
    }
}
