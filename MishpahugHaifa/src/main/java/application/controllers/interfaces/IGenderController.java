package application.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;

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
    List<String> get();

    @GetMapping(value = "/name/{name}")
    GenderEntity get(@PathVariable(name = "name") String name);

    @GetMapping(value = "/{id}")
    String get(@PathVariable(name = "id") Integer id);
}
