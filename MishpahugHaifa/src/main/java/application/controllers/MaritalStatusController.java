package application.controllers;

import java.util.List;

import application.controllers.interfaces.IMaritalStatusController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.marriagestatus.IMaritalStatusModel;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/meritalstatus")
public class MaritalStatusController implements IMaritalStatusController {

    @Autowired
    IMaritalStatusModel maritalStatusModel;
    
    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
        log.info("MaritalStatusController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("MaritalStatusController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return IConverter.PropertyToStringList(maritalStatusModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public MaritalStatusEntity get(@PathVariable(name = "id") Integer id,
                                   @RequestHeader HttpHeaders httpHeaders,
                                   HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
        log.info("MaritalStatusController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("MaritalStatusController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        System.out.println(maritalStatusModel.getById(id));
        return maritalStatusModel.getById(id);
    }

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody MaritalStatusEntity data,
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
        log.info("MaritalStatusController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("MaritalStatusController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        maritalStatusModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name,
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
        log.info("MaritalStatusController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("MaritalStatusController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        maritalStatusModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
        log.info("MaritalStatusController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("MaritalStatusController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        maritalStatusModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha {
        httpHeaders.forEach((key, value) -> {
        log.info("MaritalStatusController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("MaritalStatusController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        maritalStatusModel.deleteByID(id);
    }
}
