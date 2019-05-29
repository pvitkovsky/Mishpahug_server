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
    public void post(@RequestBody AddressEntity data) throws ExceptionMishpaha;

    public void delete();

    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;

    public List<AddressEntity> get() throws ExceptionMishpaha;

    public AddressEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha;
}
