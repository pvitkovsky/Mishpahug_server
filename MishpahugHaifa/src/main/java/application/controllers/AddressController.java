package application.controllers;

import application.controllers.intarfaces.IAddressController;
import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.address.IAddressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController implements IAddressController {

    @Autowired
    IAddressModel addressModel;

    @Override
    @PostMapping(value="/")
    public void post(@RequestBody AddressEntity data) throws ExceptionMishpaha {
        addressModel.add(data);
    }

    @Override
    @DeleteMapping(value="/")
    public void delete() {
        addressModel.deleteAll();
    }

    @Override
    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        addressModel.deleteByID(id);
    }

    @Override
    @GetMapping(value="/")
    public List<AddressEntity> get() throws ExceptionMishpaha {
        return addressModel.getAll();
    }

    @Override
    @GetMapping(value="/{id}")
    public AddressEntity get(@PathVariable(name = "id") Integer id) throws ExceptionMishpaha {
        return addressModel.getById(id);
    }
}
