package application.controllers;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/holiday")
public class HoliDaysController {

    @Autowired
    IHolyDayModel holyDayModel;

    @PostMapping(value="/")
    public void post(@RequestBody HolidayDTO[] data){
        for (HolidayDTO s: data){
            System.out.println(s);
            holyDayModel.updateFromServer(s);
        }

    }

    @DeleteMapping(value="/")
    public void delete(){
            holyDayModel.deleteAll();
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id){
        holyDayModel.deleteByID(id);
    }

    @GetMapping(value="/")
    public List<HoliDayEntity> get(){
        return holyDayModel.getAll();
    }

    @GetMapping(value="/{id}")
    public HoliDayEntity get(@PathVariable(name = "id") Integer id){
           return holyDayModel.getById(id);
    }

    @GetMapping(value="/{name}")
    public HoliDayEntity get(@RequestBody String data){
        return holyDayModel.getByName(data);
    }



}
