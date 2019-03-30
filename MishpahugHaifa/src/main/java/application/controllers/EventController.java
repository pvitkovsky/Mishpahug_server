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
    public List<EventEntity> get(@PathVariable(value = "id", required = false) Integer id,
                                 @RequestBody(required = false) HashMap<String, String> data) throws ExceptionMishpaha {
        List<EventEntity> res = new ArrayList<>();
        if (id != null){
            if (data == null) {
                res.add(eventModel.getById(id));
            }
        }
        else{
            if (data == null) {
                res = eventModel.getAll();
            }
            else {
                res = eventModel.getByFilter(data);
            }
        }
        return res;
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

    @DeleteMapping(value = "/")
    public EventEntity deleteEvent(@PathVariable(value = "id", required = false) Integer id) throws ExceptionMishpaha {
        return eventModel.remove(id);
    }
}
