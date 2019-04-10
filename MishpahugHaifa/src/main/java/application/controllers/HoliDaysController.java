package application.controllers;

import application.controllers.intarfaces.IHoliDaysController;
import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/holiday")
public class HoliDaysController implements IHoliDaysController {

    @Autowired
    IHolyDayModel holyDayModel;

    @Override
    @PostMapping(value="/")
    public void post(@RequestBody HolidayDTO[] data){
        for (HolidayDTO s: data){
            System.out.println(s);
            holyDayModel.updateFromServer(s);
        }

    }

    @Override
    @DeleteMapping(value="/")
    public void delete(){
            holyDayModel.deleteAll();
    }

    @Override
    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable(name = "id") Integer id){
        holyDayModel.deleteByID(id);
    }

    @Override
    @GetMapping(value="/")
    public List<HoliDayEntity> get(){
        return holyDayModel.getAll();
    }

    @Override
    @GetMapping(value="/{id}")
    public HoliDayEntity get(@PathVariable(name = "id") Integer id){
           return holyDayModel.getById(id);
    }
}
