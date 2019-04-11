package application.controllers;

import application.controllers.intarfaces.ICityController;
import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.city.ICityModel;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/city")
public class CityController implements ICityController {
    @Autowired
    ICityModel cityModel;
    @Override
    @PostMapping(value="/")
    public void post(@RequestBody CityEntity data) throws ExceptionMishpaha {
        cityModel.add(data);
    }

    @Override
    @PutMapping(value="/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        cityModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value="/")
    public void delete() {
        cityModel.deleteAll();
    }

    @Override
    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        cityModel.deleteByID(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<CityEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = CityEntity.class) Predicate predicate) {
        return cityModel.getAll(predicate);
    }

    @Override
    @GetMapping(value="/{id}")
    public CityEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        return cityModel.getById(id);
    }

}
