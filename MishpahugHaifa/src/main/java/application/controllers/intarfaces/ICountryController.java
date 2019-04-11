package application.controllers.intarfaces;

import application.entities.CountryEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
