package application.controllers;

import java.util.List;

import application.controllers.interfaces.IHoliDaysController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/holiday")
public class HoliDaysController implements IHoliDaysController {

    @Autowired
    IHolyDayModel holyDayModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody HolidayDTO[] data,
                     @RequestHeader HttpHeaders httpHeaders,
                     HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("HoliDaysController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("HoliDaysController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        for (HolidayDTO s : data) {
            System.out.println(s);
            holyDayModel.updateFromServer(s);
        }

    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("HoliDaysController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("HoliDaysController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        holyDayModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("HoliDaysController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("HoliDaysController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        holyDayModel.deleteByID(id);
    }

    @Override
    @GetMapping(value = "/")
    public List<HoliDayEntity> get(@RequestHeader HttpHeaders httpHeaders,
                                   HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("HoliDaysController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("HoliDaysController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return holyDayModel.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public HoliDayEntity get(@PathVariable(name = "id") Integer id,
                             @RequestHeader HttpHeaders httpHeaders,
                             HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("HoliDaysController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("HoliDaysController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return holyDayModel.getById(id);
    }
}
