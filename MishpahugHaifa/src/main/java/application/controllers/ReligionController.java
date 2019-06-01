package application.controllers;

import java.util.List;

import application.controllers.interfaces.IReligionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.ReligionEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.religion.IReligionModel;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/religion")
public class ReligionController implements IReligionController {

    @Autowired
    IReligionModel religionModel;
    
    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request) {
        return IConverter.PropertyToStringList(religionModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ReligionEntity get(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                              HttpServletRequest request) {
        return religionModel.getById(id);

    }

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody ReligionEntity data
            , @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request) {
        religionModel.add(data);

    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name
            , @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request) throws ExceptionMishpaha {
        religionModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        religionModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha {
        religionModel.deleteByID(id);
    }

}
