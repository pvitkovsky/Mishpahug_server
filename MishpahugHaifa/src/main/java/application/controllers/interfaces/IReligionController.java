package application.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import application.entities.ReligionEntity;
import application.exceptions.ExceptionMishpaha;

public interface IReligionController {
    List<String> get();

    ReligionEntity get(@PathVariable(name = "id") Integer id);

    void post(@RequestBody ReligionEntity data);

    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    void delete();

    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
