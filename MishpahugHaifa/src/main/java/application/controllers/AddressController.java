package application.controllers;

import java.util.List;

import application.controllers.interfaces.IAddressController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.address.IAddressModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/address")
public class AddressController implements IAddressController {

    @Autowired
    IAddressModel addressModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody AddressEntity data
                    ,@RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request) throws ExceptionMishpaha {
        addressModel.add(data);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        addressModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id
                      ,@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha {
        addressModel.deleteByID(id);
    }

    @Override
    @GetMapping(value = "/")
    public List<AddressEntity> get(@RequestHeader HttpHeaders httpHeaders,
                                   HttpServletRequest request) throws ExceptionMishpaha {
        return addressModel.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public AddressEntity get(@PathVariable(name = "id") Integer id
                            ,@RequestHeader HttpHeaders httpHeaders,
                             HttpServletRequest request) throws ExceptionMishpaha {
        return addressModel.getById(id);
    }
}
