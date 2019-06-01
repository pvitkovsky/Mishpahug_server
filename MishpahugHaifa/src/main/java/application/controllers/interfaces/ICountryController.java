package application.controllers.interfaces;

import application.entities.CountryEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICountryController {
    @PostMapping(value = "/")
    void post(@RequestBody CountryEntity data
            , @RequestHeader HttpHeaders httpHeaders,
              HttpServletRequest request);

    @PutMapping(value = "/")
    void post(@RequestParam(name = "id") Integer id,
              @RequestParam(name = "name") String name
            , @RequestHeader HttpHeaders httpHeaders,
              HttpServletRequest request);

    @DeleteMapping(value = "/")
    void delete(@RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);

    @GetMapping(value = "/")
    List<CountryEntity> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request);

    @GetMapping(value = "/{id}")
    CountryEntity get(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request);
}
