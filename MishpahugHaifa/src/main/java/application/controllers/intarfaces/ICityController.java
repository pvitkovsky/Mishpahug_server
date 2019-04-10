package application.controllers.intarfaces;

import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICityController {
    @PostMapping(value="/")
    void post(@RequestBody CityEntity data) throws ExceptionMishpaha;

    @PutMapping(value="/")
    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    @DeleteMapping(value="/")
    void delete();

    @DeleteMapping(value="/{id}")
    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    Iterable<CityEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = CityEntity.class) Predicate predicate);

    @GetMapping(value="/{id}")
    CityEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
