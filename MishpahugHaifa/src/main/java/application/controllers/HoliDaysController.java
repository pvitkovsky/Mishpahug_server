package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.controllers.interfaces.IHoliDaysController;
import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/holiday")
public class HoliDaysController implements IHoliDaysController {

    @Autowired
    IHolyDayModel holyDayModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody HolidayDTO[] data) {
        for (HolidayDTO s : data) {
            System.out.println(s);
            holyDayModel.updateFromServer(s);
        }

    }

    @Override
    @DeleteMapping(value = "/")
    public void delete() {
        holyDayModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        holyDayModel.deleteByID(id);
    }

    @Override
    @GetMapping(value = "/")
    public List<HoliDayEntity> get() {
        return holyDayModel.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public HoliDayEntity get(@PathVariable(name = "id") Integer id) {
        return holyDayModel.getById(id);
    }
}
