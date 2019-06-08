package application.controllers;

import java.util.List;

import application.controllers.interfaces.IKichenTypeController;
import application.exceptions.EntityExistsDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.KitchenTypeEntity;
import application.models.kichentype.IKichenTypeModel;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/kichentype")
public class KichenTypeController implements IKichenTypeController {
    @Autowired
    IKichenTypeModel kichenTypeModel;
    
    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody KitchenTypeEntity data,
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("KichenTypeController -> post -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("KichenTypeController -> post -> Remote IP -> " + request.getRemoteAddr());
        kichenTypeModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name,
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("KichenTypeController  -> put -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("KichenTypeController -> put -> Remote IP -> " + request.getRemoteAddr());
        kichenTypeModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("KichenTypeController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("KichenTypeController -> delete -> Remote IP -> " + request.getRemoteAddr());
        kichenTypeModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(@PathVariable(name = "name") String name,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("KichenTypeController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("KichenTypeController -> delete -> Remote IP -> " + request.getRemoteAddr());
        kichenTypeModel.deleteByName(name);
    }


    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("KichenTypeController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("KichenTypeController -> get -> Remote IP -> " + request.getRemoteAddr());
        return IConverter.PropertyToStringList(kichenTypeModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id", required = false) Integer id,
                      @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("KichenTypeController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("KichenTypeController -> get -> Remote IP -> " + request.getRemoteAddr());
        return kichenTypeModel.getById(id).getName();
    }

}
