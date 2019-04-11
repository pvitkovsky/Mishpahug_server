package application.controllers.intarfaces;

import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IMaritalStatusController {
    @GetMapping(value = "/")
    List<MaritalStatusEntity> get();

    @GetMapping(value = "/{id}")
    MaritalStatusEntity get(@PathVariable(name = "id") Integer id);

    @PostMapping(value = "/")
    void post(@RequestBody MaritalStatusEntity data) throws ExceptionMishpaha;

    @PutMapping(value = "/")
    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    @DeleteMapping(value = "/")
    void delete() throws ExceptionMishpaha;

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
