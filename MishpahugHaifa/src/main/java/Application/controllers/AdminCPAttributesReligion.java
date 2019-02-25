package Application.controllers;

import Application.entities.ReligionEntity;
import Application.models.religion.IReligionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/administrator/attributes/religion")
public class AdminCPAttributesReligion {
    @Autowired
    IReligionModel religionModel;
    @GetMapping(value="/getall")
    public List<ReligionEntity> getAll(){
        return religionModel.getAll();
    }
    @GetMapping(value="/getbyfullname")
    public ReligionEntity getByFullName(@RequestParam(value = "name") String name){
        return religionModel.getByFullName(name);
    }
    @GetMapping(value="/getbyname")
    public List<ReligionEntity> getByName(@RequestParam(value = "name") String name){
        return religionModel.getByName(name);
    }
    @GetMapping(value="/getbyid")
    public ReligionEntity getById(@RequestParam(value = "id") Integer id){
        return religionModel.getById(id);
    }
    @PostMapping(value = "/add")
    public ReligionEntity add(@RequestBody ReligionEntity data){
        return religionModel.add(data);
    }
    @PostMapping(value = "/update")
    public ReligionEntity update(@RequestParam(value = "name") String name){
        return religionModel.update(name);
    }
    @DeleteMapping(value = "/remove")
    public ReligionEntity remove(@RequestParam(value = "id") Integer id){
        return religionModel.remove(id);
    }
}
