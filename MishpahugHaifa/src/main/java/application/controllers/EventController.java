package application.controllers;

import application.dto.EventDTO;
import application.dto.EventDTOLists;
import application.entities.EventEntity;
import application.models.city.ICityModel;
import application.models.country.ICountryModel;
import application.models.event.IEventModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.religion.IReligionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    IEventModel eventModel;

    @Autowired
    IHolyDayModel holyDayModel;

    @Autowired
    ICountryModel countryModel;

    @Autowired
    ICityModel cityModel;

    @Autowired
    IReligionModel religionModel;

    @Autowired
    IKichenTypeModel kichenTypeModel;


    @GetMapping(value="/getlists")
    public EventDTOLists getDataForAddForm(){
        EventDTOLists eventDTOLists = new EventDTOLists();
        eventDTOLists.setHoliDayEntities(holyDayModel.getAll());
        eventDTOLists.setKichenTypeEntities(kichenTypeModel.getAll());
        eventDTOLists.setReligionEntities(religionModel.getAll());
        return eventDTOLists;
    }

    @PostMapping(value="/add")
    public EventEntity setDataFromForm(@RequestBody EventDTO data){
        EventEntity eventEntity = new EventEntity();
        eventEntity.convertEventDTO(data);
        eventEntity.setKichenTypeEntity(kichenTypeModel.getByName(data.getKichenType()));
        eventEntity.setHoliDayEntity(holyDayModel.getByName(data.getHoliday()));
        return eventModel.add(eventEntity);
    }


}
