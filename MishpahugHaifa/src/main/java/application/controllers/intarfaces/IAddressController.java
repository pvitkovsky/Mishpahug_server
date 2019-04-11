package application.controllers.intarfaces;

import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IAddressController {
    @PostMapping(value = "/")
    void post(@RequestBody AddressEntity data) throws ExceptionMishpaha;

    @DeleteMapping(value = "/")
    void delete();

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;

    @GetMapping(value = "/")
    List<AddressEntity> get() throws ExceptionMishpaha;

    @GetMapping(value = "/{id}")
    AddressEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
