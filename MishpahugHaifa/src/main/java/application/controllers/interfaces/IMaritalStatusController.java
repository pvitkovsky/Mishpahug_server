package application.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;

public interface IMaritalStatusController {
    List<String> get();

    MaritalStatusEntity get(@PathVariable(name = "id") Integer id);

    void post(@RequestBody MaritalStatusEntity data) throws ExceptionMishpaha;

    void put(@RequestParam(name = "id") Integer id,
             @RequestParam(name = "name") String name) throws ExceptionMishpaha;

    void delete() throws ExceptionMishpaha;

    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
