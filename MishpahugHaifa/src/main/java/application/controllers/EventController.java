package application.controllers;

import application.controllers.intarfaces.IEventController;
import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.event.IEventModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
@Slf4j
@RestController
@RequestMapping(value = "/event")
public class EventController implements IEventController {

    @Autowired
    IEventModel eventModel;

    @Autowired
    IHolyDayModel holyDayModel;

    @Autowired
    IKichenTypeModel kichenTypeModel;

    /* (non-Javadoc)
	 * @see application.controllers.intarfaces.IEventController#findAllByWebQuerydsl(com.querydsl.core.types.Predicate)
	 */
    @Override
	@RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<EventEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = EventEntity.class) Predicate predicate
            ,@RequestHeader HttpHeaders httpHeaders) {
        log.info(String.valueOf(httpHeaders));
        return eventModel.getAll(predicate);
    }

    /* (non-Javadoc)
	 * @see application.controllers.intarfaces.IEventController#findAll(java.lang.Integer)
	 */
    @Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public EventEntity findAll(@PathVariable(name = "id") Integer id
            ,@RequestHeader HttpHeaders httpHeaders) throws ExceptionMishpaha {
        log.info(String.valueOf(httpHeaders));
        return eventModel.getById(id);
    }


    /* (non-Javadoc)
	 * @see application.controllers.intarfaces.IEventController#setDataFromForm(application.dto.EventDTO)
	 */
    @Override
	@PostMapping(value="/")
    public EventEntity setDataFromForm(@RequestBody EventDTO data){
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
	@PutMapping(value="/{id}")
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
