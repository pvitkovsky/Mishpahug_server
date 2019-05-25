package application.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import application.entities.KitchenTypeEntity;
import application.exceptions.ExceptionMishpaha;

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
