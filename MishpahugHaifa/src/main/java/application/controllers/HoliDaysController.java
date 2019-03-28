package application.controllers;

import application.dto.forholiday.HolidayDTO;
import application.models.holyday.IHolyDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
