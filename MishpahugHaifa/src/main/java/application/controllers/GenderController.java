package application.controllers;

import java.util.List;

import application.controllers.interfaces.IGenderController;
import application.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.GenderEntity;
import application.models.gender.IGenderModel;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/gender")
public class GenderController implements IGenderController {
    @Autowired
    IGenderModel genderModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody GenderEntity data,
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("GenderController > post -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("GenderController -> post -> Remote IP -> " + request.getRemoteAddr());
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name,
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("GenderController -> put -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("GenderController -> put -> Remote IP -> " + request.getRemoteAddr());
        genderModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(@PathVariable(name = "name") String name,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("GenderController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("GenderController -> delete -> Remote IP -> " + request.getRemoteAddr());
        genderModel.deleteByName(name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("GenderController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("GenderController -> delete -> Remote IP -> " + request.getRemoteAddr());
        genderModel.deleteAll();

    }

    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("GenderController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("GenderController -> get -> Remote IP -> " + request.getRemoteAddr());
        return IConverter.PropertyToStringList(genderModel.getAll());
    }

    @Override
    @GetMapping(value = "/name/{name}")
    public GenderEntity get(@PathVariable(name = "name") String name,
                            @RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("GenderController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("GenderController -> get -> Remote IP -> " + request.getRemoteAddr());
        return genderModel.getByName(name);

    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id") Integer id,
                      @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("GenderController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("GenderController -> get -> Remote IP -> " + request.getRemoteAddr());
        GenderEntity genderEntity = genderModel.getById(id);
        return genderEntity.getName();
    }

}
