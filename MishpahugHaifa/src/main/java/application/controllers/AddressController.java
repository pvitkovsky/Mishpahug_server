package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.controllers.interfaces.IAddressController;
import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.address.IAddressModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/address")
public class AddressController implements IAddressController {

    @Autowired
    IAddressModel addressModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody AddressEntity data) throws ExceptionMishpaha {
        addressModel.add(data);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() {
        addressModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        addressModel.deleteByID(id);
    }

    @Override
    @GetMapping(value = "/")
    public List<AddressEntity> get() throws ExceptionMishpaha {
        return addressModel.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public AddressEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        return addressModel.getById(id);
    }
}
