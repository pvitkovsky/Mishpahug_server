package application.controllers;

import application.controllers.intarfaces.IReligionController;
import application.entities.ReligionEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.religion.IReligionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/religion")
public class ReligionController implements IReligionController {

    @Autowired
    IReligionModel religionModel;

    @Override
    @GetMapping(value = "/")
    public List<ReligionEntity> get() {
        return religionModel.getAll();

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
