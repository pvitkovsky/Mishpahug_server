package application.controllers;

import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.city.ICityModel;
import application.models.country.ICountryModel;
import application.models.event.IEventModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.religion.IReligionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    IEventModel eventModel;

    @Autowired
    IHolyDayModel holyDayModel;

    @Autowired
    IKichenTypeModel kichenTypeModel;


    @GetMapping(value="/{id}")
    public EventEntity get(@PathVariable(value = "id", required = false) Integer id) throws ExceptionMishpaha {
               return eventModel.getById(id);
    }

    @GetMapping(value="/")
    public List<EventEntity> get() {
        return eventModel.getAll();
    }

    @GetMapping(value="/{filter}")
    public List<EventEntity> get(@RequestBody HashMap<String, String> data) {
        return eventModel.getByFilter(data);
    }

    @PostMapping(value="/")
    public EventEntity setDataFromForm(@RequestBody EventDTO data){
        EventEntity eventEntity = new EventEntity();
        eventEntity.convertEventDTO(data);
        eventEntity.setKitchenType(kichenTypeModel.getByName(data.getKichenType()));
        eventEntity.setHoliDay(holyDayModel.getByName(data.getHoliday()));
        return eventModel.add(eventEntity);
    }

    @PutMapping(value="/{id}")
    public EventEntity updateDataFromForm(@RequestBody HashMap<String, String> data,
                                          @PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return eventModel.update(id, data);
    }

    @DeleteMapping(value = "/{id}")
    public EventEntity delete(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return eventModel.delete(id);
    }

    @DeleteMapping(value = "/")
    public void delete() throws ExceptionMishpaha {
        eventModel.deleteAll();
    }
}
