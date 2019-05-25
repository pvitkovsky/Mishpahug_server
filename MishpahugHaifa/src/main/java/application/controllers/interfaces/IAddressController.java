package application.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;

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
