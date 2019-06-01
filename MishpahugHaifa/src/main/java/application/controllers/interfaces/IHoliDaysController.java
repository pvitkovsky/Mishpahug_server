package application.controllers.interfaces;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IHoliDaysController {
    @PostMapping(value = "/")
    void post(@RequestBody HolidayDTO[] data
            , @RequestHeader HttpHeaders httpHeaders,
              HttpServletRequest request);

    @DeleteMapping(value = "/")
    void delete(@RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);

    @GetMapping(value = "/")
    List<HoliDayEntity> get(@RequestHeader HttpHeaders httpHeaders,
                            HttpServletRequest request);

    @GetMapping(value = "/{id}")
    HoliDayEntity get(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                      HttpServletRequest request);
}
