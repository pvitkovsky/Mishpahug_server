package application.controllers;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/holidays")
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
    public void delete(@RequestParam(name = "id", required = false) Integer id){
        if (id == null){

        }
        else{
            
        }
    }

    @GetMapping(value="/")
    public List<HoliDayEntity> get(@RequestParam(name = "id", required = false) Integer id,
                                   @RequestBody(required = false)HashMap<String, String> data){
        if (id != null && data == null){

        }
        if (id == null && data != null){

        }
        if (id == null && data == null){
            return holyDayModel.getAll();
        }
        return null;
    }
}
