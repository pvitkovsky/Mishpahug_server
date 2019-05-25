package application.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import application.entities.CountryEntity;

public interface ICountryController {
    @PostMapping(value = "/")
    void post(@RequestBody CountryEntity data);

    @PutMapping(value = "/")
    void post(@RequestParam(name = "id") Integer id,
              @RequestParam(name = "name") String name);

    @DeleteMapping(value = "/")
    void delete();

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/")
    List<CountryEntity> get();

    @GetMapping(value = "/{id}")
    CountryEntity get(@PathVariable(name = "id") Integer id);
}
