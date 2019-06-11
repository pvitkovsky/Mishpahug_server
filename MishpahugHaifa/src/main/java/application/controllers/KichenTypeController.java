package application.controllers;

import java.util.List;

import application.controllers.interfaces.IKichenTypeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.KitchenTypeEntity;
import application.models.kichentype.IKichenTypeModel;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

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
        
        
        kichenTypeModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name,
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request){
        
        
        kichenTypeModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        
        
        kichenTypeModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(@PathVariable(name = "name") String name,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        
        
        kichenTypeModel.deleteByName(name);
    }


    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        
        
        return IConverter.PropertyToStringList(kichenTypeModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id", required = false) Integer id,
                      @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request){
        
        
        return kichenTypeModel.getById(id).getName();
    }

}
