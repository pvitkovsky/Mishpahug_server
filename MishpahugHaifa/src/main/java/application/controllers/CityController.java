package application.controllers;

import application.controllers.interfaces.ICityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;
import application.models.city.ICityModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController implements ICityController {
    @Autowired
    ICityModel cityModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody CityEntity data,
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request){
        
        
        cityModel.add(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name,
                    @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request){
        
        
        cityModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        
        
        cityModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(@PathVariable(name = "name") String name,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        
        
        cityModel.deleteByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public List<String> get(@QuerydslPredicate(root = CityEntity.class) Predicate predicate,
                            @RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request){
        
        
        Iterable<CityEntity> cityEntityList = cityModel.getAll(predicate);
        List<String> res = new ArrayList<>();
        cityEntityList.forEach(item -> res.add(item.getName()));
        return res;
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id") Integer id,
                      @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request){
        
        
        return cityModel.getById(id).getName();
    }

}
