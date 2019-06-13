package application.controllers;

import application.controllers.interfaces.IAddressController;
import application.entities.properties.AddressEntity;
import application.models.address.IAddressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/address")
public class AddressController implements IAddressController {

    @Autowired
    IAddressModel addressModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request, @RequestBody AddressEntity data){
        addressModel.add(data);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        addressModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request, @PathVariable(name = "id") Integer id){
        addressModel.deleteByID(id);
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request, @PathVariable(name = "id") Integer id){
        return addressModel.getById(id).toString();
    }
}
