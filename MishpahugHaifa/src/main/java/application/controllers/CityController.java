package application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import application.controllers.interfaces.ICityController;
import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.city.ICityModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/city")
public class CityController implements ICityController {
    @Autowired
    ICityModel cityModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody CityEntity data) throws ExceptionMishpaha {
        cityModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name) throws ExceptionMishpaha {
        cityModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() {
        cityModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
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
    @GetMapping(value = "/{id}")
    public CityEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        return cityModel.getById(id);
    }

}
