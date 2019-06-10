package application.controllers;

import application.controllers.interfaces.IEventController;
import application.dto.EventDTO;
import application.dto.UserDTO;
import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.models.event.IEventModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import application.utils.converter.IConverter;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
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
    IConverter<UserEntity, UserDTO> converterUser;

    @Autowired
    IHolyDayModel holyDayModel;

    @Autowired
    IKichenTypeModel kichenTypeModel;

    
    //TODO:  events by owner;  events by subcscriber; check that no wrapping is done;  
    
    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#get(com.querydsl.core.types.Predicate)
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public List<EventDTO> findAllByWebQuerydsl(@QuerydslPredicate(root = EventEntity.class) Predicate predicate,
                                               @RequestHeader HttpHeaders httpHeaders,
                                               HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {

            log.info("EventController -> findAllByWebQuerydsl -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());

            log.info("EventController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> get -> Remote IP -> " + request.getRemoteAddr());
>>>>>>> parent of 395ebff... Preparation of controllers for the aspect-oriented refactoring
=======
            log.info("EventController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> get -> Remote IP -> " + request.getRemoteAddr());
>>>>>>> parent of 395ebff... Preparation of controllers for the aspect-oriented refactoring
        return converter.DTOListFromEntities(eventModel.getAll(predicate));
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#findAll(java.lang.Integer)
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public EventDTO findById(@PathVariable(name = "id") Integer id,
                             @RequestHeader HttpHeaders httpHeaders,
                             HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("EventController -> findById -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> findById -> Remote IP -> " + request.getRemoteAddr());
        return new EventDTO(eventModel.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/guests")
    @ResponseBody
    public List<UserDTO> findGuestByEventId(@PathVariable(name = "id") Integer id,
                                            @RequestHeader HttpHeaders httpHeaders,
                                            HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("EventController -> findGuestByEventId -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> findGuestByEventId -> Remote IP -> " + request.getRemoteAddr());
        Set<SubscriptionEntity> subscriptionEntityList = eventModel.getById(id).getSubscriptions();
        List<UserEntity> userEntityList = new ArrayList<>();
        for (SubscriptionEntity x:subscriptionEntityList
             ) {
            userEntityList.add(x.getGuest());
        }
        return converterUser.DTOListFromEntities(userEntityList);
    }

    @Override
    @GetMapping(value = "/byowner/{ownerusername}")
    public List<EventDTO> getByOwner(@PathVariable(value = "ownerusername") String ownerusername,
                                     @RequestHeader HttpHeaders httpHeaders,
                                     HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("EventController -> getByOwner -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> getByOwner -> Remote IP -> " + request.getRemoteAddr());
        return converter.DTOListFromEntities(eventModel.getByOwner(ownerusername));
    }


    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#setDataFromForm(application.dto.EventDTO)
     */
    @Override
    @PostMapping(value = "/")
    public EventDTO setDataFromForm(@RequestBody EventDTO data,
                                    @RequestHeader HttpHeaders httpHeaders,
                                    HttpServletRequest request){
        EventEntity eventEntity = new EventEntity();
        eventEntity.convertEventDTO(data);
        eventEntity.setKitchenType(kichenTypeModel.getByName(data.getKichenType()));
        eventEntity.setHoliDay(holyDayModel.getByName(data.getHoliday()));
        httpHeaders.forEach((key, value) -> {
            log.info("EventController -> setDataFromForm -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> setDataFromForm -> Remote IP -> " + request.getRemoteAddr());
        return new EventDTO(eventModel.add(eventEntity));
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#updateDataFromForm(java.util.HashMap, java.lang.Integer)
     */
    @Override
    @PutMapping(value = "/{id}")
    public EventDTO updateDataFromForm(@RequestBody HashMap<String, String> data,
                                       @PathVariable(value = "id") Integer id,
                                       @RequestHeader HttpHeaders httpHeaders,
                                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("EventController -> updateDataFromForm -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        data.forEach((key, value) -> {
            log.info("EventController -> updateDataFromForm -> data -> " + String.format("data '%s' = %s", key, value));
        });
        log.info("EventController -> updateDataFromForm -> Remote IP -> " + request.getRemoteAddr());
        return new EventDTO(eventModel.update(id, data));
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#delete(java.lang.Integer)
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Integer id,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        eventModel.getById(id).putIntoDeletionQueue();
        httpHeaders.forEach((key, value) -> {
            log.info("EventController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> delete -> Remote IP -> " + request.getRemoteAddr());
        eventModel.delete(id);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#delete()
     */
    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("EventController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("EventController -> delete -> Remote IP -> " + request.getRemoteAddr());
<<<<<<< HEAD
<<<<<<< HEAD
        Predicate predicate = new Predicate() {
            @Override
            public Predicate not() {
                return null;
            }

            @Nullable
            @Override
            public <R, C> R accept(Visitor<R, C> v, @Nullable C context) {
                return null;
            }

            @Override
            public Class<? extends Boolean> getType() {
                return null;
            }
        };
        eventModel.getAll(predicate).forEach(EventEntity::putIntoDeletionQueue);
        eventModel.deleteAll();
    }
	
=======
        eventModel.getAll().forEach(EventEntity::putIntoDeletionQueue);
        eventModel.deleteAll();
    }
=======
        eventModel.getAll().forEach(EventEntity::putIntoDeletionQueue);
        eventModel.deleteAll();
    }
}
