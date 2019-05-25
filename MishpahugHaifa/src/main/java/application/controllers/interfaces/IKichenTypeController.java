package application.controllers.interfaces;

import application.entities.KitchenTypeEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IKichenTypeController {
    @PostMapping(value = "/")
    void post(@RequestBody KitchenTypeEntity data) throws ExceptionMishpaha;

    @PutMapping(value = "/")
    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name);

    @DeleteMapping(value = "/")
    void delete();

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/")
    List<String> get();

    @GetMapping(value = "/{id}")
    String get(@PathVariable(name = "id", required = false) Integer id);
}
