package application.controllers.interfaces;

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

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;

public interface ICityController {
    void post(@RequestBody CityEntity data) throws ExceptionMishpaha;

    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    void delete();

    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;

    Iterable<CityEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = CityEntity.class) Predicate predicate);

    CityEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
