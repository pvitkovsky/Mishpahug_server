package application.controllers.intarfaces;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IHoliDaysController {
    @PostMapping(value="/")
    void post(@RequestBody HolidayDTO[] data);

    @DeleteMapping(value="/")
    void delete();

    @DeleteMapping(value="/{id}")
    void delete(@PathVariable(name = "id") Integer id);

    @GetMapping(value="/")
    List<HoliDayEntity> get();

    @GetMapping(value="/{id}")
    HoliDayEntity get(@PathVariable(name = "id") Integer id);
}
