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
    void post(@RequestBody GenderEntity data) throws ExceptionMishpaha;

    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;

    void delete();

    List<String> get();

    GenderEntity get(@PathVariable(name = "name") String name);

    String get(@PathVariable(name = "id") Integer id);
}
