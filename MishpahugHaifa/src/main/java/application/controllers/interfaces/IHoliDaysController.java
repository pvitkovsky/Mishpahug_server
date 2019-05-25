package application.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;

public interface IHoliDaysController {
    @PostMapping(value = "/")
    void post(@RequestBody HolidayDTO[] data);

    @DeleteMapping(value = "/")
    void delete();

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/")
    List<HoliDayEntity> get();

    @GetMapping(value = "/{id}")
    HoliDayEntity get(@PathVariable(name = "id") Integer id);
}
