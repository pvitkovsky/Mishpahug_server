package application.controllers.interfaces;

import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface ICityController {
    @PostMapping(value = "/")
    void post(@RequestBody CityEntity data
            , @RequestHeader HttpHeaders httpHeaders,
              HttpServletRequest request) throws ExceptionMishpaha;

    @PutMapping(value = "/")
    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name
            , @RequestHeader HttpHeaders httpHeaders,
             HttpServletRequest request) throws ExceptionMishpaha;

    @DeleteMapping(value = "/")
    void delete(@RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request) throws ExceptionMishpaha;

    @RequestMapping(method = RequestMethod.GET, value = "/")
                                                                           @ResponseBody
    Iterable<CityEntity> findAllByWebQuerydsl(@QuerydslPredicate(root = CityEntity.class) Predicate predicate
            , @RequestHeader HttpHeaders httpHeaders,
                                              HttpServletRequest request);

    @GetMapping(value = "/{id}")
    CityEntity get(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                   HttpServletRequest request) throws ExceptionMishpaha;
}
