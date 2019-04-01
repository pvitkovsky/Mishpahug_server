package application.controllers;

import application.entities.ReligionEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.religion.IReligionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/religion")
public class ReligionController {

    @Autowired
    IReligionModel religionModel;

    @GetMapping(value="/")
    public List<ReligionEntity> get(){
        return religionModel.getAll();

    }

    @GetMapping(value="/{id}")
    public ReligionEntity get(@PathVariable(name = "id") Integer id){
        return religionModel.getById(id);

    }

    @PostMapping(value="/")
    public void post(@RequestBody ReligionEntity data) {
        religionModel.add(data);

    }

    @PutMapping(value="/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        religionModel.updateName(id, name);
    }

    @DeleteMapping(value="/")
    public void delete() {
        religionModel.deleteAll();
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        religionModel.deleteByID(id);
    }

}
