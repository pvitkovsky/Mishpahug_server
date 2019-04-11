package application.controllers.intarfaces;

import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IGenderController {
    @PostMapping(value = "/")
    void post(@RequestBody GenderEntity data) throws ExceptionMishpaha;

    @PutMapping(value = "/")
    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;

    @DeleteMapping(value = "/")
    void delete();

    @GetMapping(value = "/")
    List<GenderEntity> get();

    @GetMapping(value = "/name/{name}")
    GenderEntity get(@PathVariable(name = "name") String name);

    @GetMapping(value = "/{id}")
    GenderEntity get(@PathVariable(name = "id") Integer id);
}
