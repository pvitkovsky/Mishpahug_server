package application.controllers.interfaces;

import application.entities.KitchenTypeEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IKichenTypeController {
    @PostMapping(value = "/")
    void post(@RequestBody KitchenTypeEntity data
            , @RequestHeader HttpHeaders httpHeaders,
              HttpServletRequest request) throws ExceptionMishpaha;

    @PutMapping(value = "/")
    void put(@RequestParam(name = "id") Integer id,
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
    List<String> get(@RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request);

    @GetMapping(value = "/{id}")
    String get(@PathVariable(name = "id", required = false) Integer id
            , @RequestHeader HttpHeaders httpHeaders,
               HttpServletRequest request);
}
