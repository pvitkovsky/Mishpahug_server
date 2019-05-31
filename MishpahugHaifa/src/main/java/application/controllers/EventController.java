package application.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import application.dto.UserDTO;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.utils.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import application.controllers.interfaces.IEventController;
import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.event.IEventModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/event")
public class EventController implements IEventController {

    @Autowired
    IEventModel eventModel;

    @Autowired
    IConverter<EventEntity, EventDTO> converter;

    @Autowired
    IHolyDayModel holyDayModel;

    @Autowired
    IKichenTypeModel kichenTypeModel;

    
    //TODO:  events by owner;  events by subcscriber; check that no wrapping is done;  
    
    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#findAllByWebQuerydsl(com.querydsl.core.types.Predicate)
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public List<EventDTO> findAllByWebQuerydsl(
            @QuerydslPredicate(root = EventEntity.class) Predicate predicate
            , @RequestHeader HttpHeaders httpHeaders,
            HttpServletRequest request) {
        log.info("EventController -> findAllByWebQuerydsl -> Headers -> " + httpHeaders);
        log.info("EventController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return converter.DTOListFromEntities(eventModel.getAll(predicate));
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#findAll(java.lang.Integer)
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public EventEntity findById(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                                HttpServletRequest request) throws ExceptionMishpaha {
        log.info("EventController -> findAllByWebQuerydsl -> Headers -> " + httpHeaders);
        log.info("EventController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return eventModel.getById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/guests")
    @ResponseBody
    public List<UserEntity> findGestByEventId(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                                              HttpServletRequest request) throws ExceptionMishpaha {
        log.info("EventController -> findAllByWebQuerydsl -> Headers -> " + httpHeaders);
        log.info("EventController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        Set<SubscriptionEntity> subscriptionEntityList = eventModel.getById(id).getSubscriptions();
        List<UserEntity> userEntityList = new ArrayList<>();
        for (SubscriptionEntity x:subscriptionEntityList
             ) {
            userEntityList.add(x.getGuest());
        }
        return userEntityList;
    }

    @Override
    @GetMapping(value = "/byowner/{ownerusername}")
    public List<EventDTO> getByOwner(@PathVariable(value = "ownerusername") String ownerusername){
        return converter.DTOListFromEntities(eventModel.getByOwner(ownerusername));
    }


    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#setDataFromForm(application.dto.EventDTO)
     */
    @Override
    @PostMapping(value = "/")
    public EventEntity setDataFromForm(@RequestBody EventDTO data) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.convertEventDTO(data);
        eventEntity.setKitchenType(kichenTypeModel.getByName(data.getKichenType()));
        eventEntity.setHoliDay(holyDayModel.getByName(data.getHoliday()));
        return eventModel.add(eventEntity);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#updateDataFromForm(java.util.HashMap, java.lang.Integer)
     */
    @Override
    @PutMapping(value = "/{id}")
    public EventEntity updateDataFromForm(@RequestBody HashMap<String, String> data,
                                          @PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return eventModel.update(id, data);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#delete(java.lang.Integer)
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public EventEntity delete(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        eventModel.getById(id).putIntoDeletionQueue();
        return eventModel.delete(id);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#delete()
     */
    @Override
    @DeleteMapping(value = "/")
    public void delete() throws ExceptionMishpaha {
        eventModel.getAll().forEach(EventEntity::putIntoDeletionQueue);
        eventModel.deleteAll();
    }
}
