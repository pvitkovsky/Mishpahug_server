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
    void post(@RequestBody CountryEntity data);

    void post(@RequestParam(name = "id") Integer id,
              @RequestParam(name = "name") String name);

    void delete();

    void delete(@PathVariable(name = "id") Integer id);

    List<CountryEntity> get();

    CountryEntity get(@PathVariable(name = "id") Integer id);
}
