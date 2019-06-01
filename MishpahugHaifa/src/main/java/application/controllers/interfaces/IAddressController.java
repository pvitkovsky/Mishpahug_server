package application.controllers.interfaces;

import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAddressController {
    @PostMapping(value = "/")
    void post(@RequestBody AddressEntity data
            , @RequestHeader HttpHeaders httpHeaders,
              HttpServletRequest request) throws ExceptionMishpaha;

    @DeleteMapping(value = "/")
    void delete(@RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request) throws ExceptionMishpaha;

    @GetMapping(value = "/")
    List<AddressEntity> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request) throws ExceptionMishpaha;

    @GetMapping(value = "/{id}")
    AddressEntity get(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request) throws ExceptionMishpaha;
}
