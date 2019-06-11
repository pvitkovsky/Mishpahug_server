package application.controllers;

import application.controllers.interfaces.IMaritalStatusController;
import application.entities.MaritalStatusEntity;
import application.models.marriagestatus.IMaritalStatusModel;
import application.utils.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/meritalstatus")
public class MaritalStatusController implements IMaritalStatusController {

    @Autowired
    IMaritalStatusModel maritalStatusModel;
    
    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        return IConverter.PropertyToStringList(maritalStatusModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public MaritalStatusEntity get(
                                   @RequestHeader HttpHeaders httpHeaders,
                                   HttpServletRequest request, @PathVariable(name = "id") Integer id){
        return maritalStatusModel.getById(id);
    }

    @Override
    @PostMapping(value = "/")
    public void post(
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request, @RequestBody MaritalStatusEntity data){
        maritalStatusModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request, @RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name){
        maritalStatusModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        maritalStatusModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request, @PathVariable(name = "name") String name){
        maritalStatusModel.deleteByName(name);
    }
}
