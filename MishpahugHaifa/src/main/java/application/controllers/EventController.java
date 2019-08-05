package application.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.dto.UserDTO;
import application.models.event.EventEntity;
import application.models.event.IEventModel;
import application.models.relation.IRelationModel;
import application.models.user.IUserModel;
import application.models.user.UserEntity;
import application.utils.converter.IStrongEntityConverter;
import application.utils.converter.IWeakEntityConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/event")
public class EventController implements IEventController {

	@Autowired
	IUserModel userModel;
	
	@Autowired
	IEventModel eventModel;

	@Autowired
	IRelationModel relationModel;

	@Autowired
	IWeakEntityConverter<EventEntity, UserEntity, EventDTO> converterEvent;

	@Autowired
	IStrongEntityConverter<UserEntity, UserDTO> converterUser;

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public Page<EventDTO> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, 
			@QuerydslPredicate(root = EventEntity.class) Predicate predicate, Pageable pageable) {
		return converterEvent.DTOPageFromEntities(eventModel.getAll(predicate, pageable));
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EventDTO findById(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "id") Integer id) { //TODO: two queries into one
		EventEntity event = eventModel.getById(id);
		EventDTO eventDTO  = new EventDTO(event);
		eventDTO.setGuestIds(relationModel.getGuestIdsByEvent(event)); 
		return eventDTO;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/guests")
	@ResponseBody
	@Override
	/** inter-aggregate query**/  // implementation : jpql join query 
	public List<UserDTO> findGuestByEventId(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, @PathVariable(name = "id") Integer id) {	/*inter-aggregate query*/
		List<UserEntity> userEntityList = relationModel.getSubscribedGuests(id);
		return converterUser.DTOListFromEntities(userEntityList);
	}

	@Override
	@PostMapping(value = "/")
	public EventDTO setDataFromForm(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody EventDTO eventDTO) {
		UserEntity owner = userModel.getById(eventDTO.getOwnerId());
		EventEntity eventEntity = converterEvent.entityFromDTO(eventDTO, owner);
		return new EventDTO(eventModel.add(eventEntity));
	}

	@Override
	@PutMapping(value = "/{id}")
	public EventDTO updateDataFromForm(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody HashMap<String, String> data, @PathVariable(value = "id") Integer id) {
		data.forEach((key, value) -> {
			log.warn("EventController -> updateDataFromForm -> data -> " + String.format("data '%s' = %s", key, value));
		});
		return new EventDTO(eventModel.update(id, data));
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(value = "id") Integer id) {
		eventModel.delete(id);
	}

}
