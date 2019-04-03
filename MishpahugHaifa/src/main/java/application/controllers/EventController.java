package application.controllers;

import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.event.IEventModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    IEventModel eventModel;

    @Autowired
    IHolyDayModel holyDayModel;

    @Autowired
    IKichenTypeModel kichenTypeModel;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<EventEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = EventEntity.class) Predicate predicate) {
        return eventModel.getAll(predicate);
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
