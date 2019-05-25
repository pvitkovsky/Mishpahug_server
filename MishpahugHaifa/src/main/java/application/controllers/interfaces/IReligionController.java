package application.controllers.interfaces;

import application.entities.ReligionEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IReligionController {
    @GetMapping(value = "/")
    List<String> get();

    @GetMapping(value = "/{id}")
    ReligionEntity get(@PathVariable(name = "id") Integer id);

    @PostMapping(value = "/")
    void post(@RequestBody ReligionEntity data);

    @PutMapping(value = "/")
    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    @DeleteMapping(value = "/")
    void delete();

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
