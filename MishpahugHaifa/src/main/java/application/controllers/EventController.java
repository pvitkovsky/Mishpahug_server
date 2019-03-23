package application.controllers;

import application.dto.EventDTO;
import application.dto.EventDTODetail;
import application.dto.EventDTOLists;
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
    @PostMapping(value="/addPage2")
    public EventDTODetail setDataFromFormDetail(@RequestBody EventDTODetail data){

        return null;

    }

    @PostMapping(value="/addPage1")
    public EventDTO setDataFromForm(@RequestBody EventDTO data){
        return null;

    }
}
