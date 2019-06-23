package application.controllers;

import application.controllers.interfaces.IReligionController;
import application.dto.PropertyDTO;
import application.entities.properties.ReligionEntity;
import application.models.properties.religion.IReligionModel;
import application.utils.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/religion")
public class ReligionController implements IReligionController {

    @Autowired
    IReligionModel religionModel;
    
    @Override
    @GetMapping(value = "/")
    public List<PropertyDTO> get(@RequestHeader HttpHeaders httpHeaders,
                                 HttpServletRequest request){
        return IConverter.PropertyToDTOList(religionModel.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public PropertyDTO get(@RequestHeader HttpHeaders httpHeaders,
                              HttpServletRequest request, @PathVariable(name = "id") Integer id){
        return new PropertyDTO(id, religionModel.getById(id).getName());
    }

    @Override
    @PostMapping(value = "/")
    public void post(@RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request, @RequestBody ReligionEntity data){
        religionModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request, @RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name){
        religionModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        religionModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request, @PathVariable(name = "name") String name){
        religionModel.deleteByName(name);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(HttpHeaders httpHeaders, HttpServletRequest request, @PathVariable(name = "id") Integer id) {
        religionModel.deleteByID(id);
    }

}
