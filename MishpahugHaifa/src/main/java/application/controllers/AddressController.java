package application.controllers;

import application.controllers.interfaces.IAddressController;
import application.exceptions.EntityExistsDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.AddressEntity;
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
    public void post(@RequestBody AddressEntity data,
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("AddressController -> post -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("AddressController -> post -> Remote IP -> " + request.getRemoteAddr());
        addressModel.add(data);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("AddressController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("AddressController -> delete -> Remote IP -> " + request.getRemoteAddr());
        addressModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("AddressController -> delete{" + id + "} -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("AddressController -> delete{" + id + "} -> Remote IP -> " + request.getRemoteAddr());
        addressModel.deleteByID(id);
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id") Integer id,
                      @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("AddressController -> get{" + id + "} -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("AddressController -> get{" + id + "} -> Remote IP -> " + request.getRemoteAddr());
        return addressModel.getById(id).toString();
    }
}
