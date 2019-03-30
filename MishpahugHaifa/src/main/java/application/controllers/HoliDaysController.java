package application.controllers;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            holyDayModel.deleteAll();
        }
        else{
            holyDayModel.deleteByID(id);
        }
    }

    @GetMapping(value="/")
    public List<HoliDayEntity> get(@RequestParam(name = "id", required = false) Integer id,
                                   @RequestBody(required = false) String data){
        List<HoliDayEntity> res = new ArrayList<>();
        if (id != null && data == null){
            res.add(holyDayModel.getById(id));
        }
        if (id == null && data != null){
            res.add(holyDayModel.getByName(data));
        }
        if (id == null && data == null){
            res = holyDayModel.getAll();
        }
        return res;
    }
}
