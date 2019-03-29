package application.controllers;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/holidays")
public class HoliDaysController {

    @Autowired
    IHolyDayModel holyDayModel;

    @PostMapping(value="/addholiday")
    public void addHoliday(@RequestBody HolidayDTO[] data){
        for (HolidayDTO s: data){
            System.out.println(s);
            holyDayModel.updateFromServer(s);
        }

    }

    @GetMapping(value="/getall")
    public List<HoliDayEntity> getAll(){
            return holyDayModel.getAll();
    }

    @GetMapping(value="/getallby")
    public List<HoliDayEntity> getAllBy(){
        return null; //TODO
    }
}
