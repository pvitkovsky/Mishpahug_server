package application.controllers;

import application.controllers.interfaces.IEventController;
import application.dto.EventDTO;
import application.dto.UserDTO;
import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.models.event.IEventModel;
import application.models.properties.holyday.IHolyDayModel;
import application.models.properties.kichentype.IKichenTypeModel;
import application.models.user.IUserModel;
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

	@Autowired
	IUserModel userModel;

	// TODO: events by owner; events by subcscriber; check that no wrapping is done;

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public List<EventDTO> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@QuerydslPredicate(root = EventEntity.class) Predicate predicate) {
		return converter.DTOListFromEntities(eventModel.getAll(predicate));
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EventDTO findById(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "id") Integer id) {
		return new EventDTO(eventModel.getById(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/guests")
	@ResponseBody
	@Override
	public List<UserDTO> findGuestByEventId(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, @PathVariable(name = "id") Integer id) {
		Set<SubscriptionEntity> subscriptionEntityList = eventModel.getById(id).getSubscriptions();
		List<UserEntity> userEntityList = new ArrayList<>();
		for (SubscriptionEntity x : subscriptionEntityList) {
			userEntityList.add(x.getGuest());
		}
		return converterUser.DTOListFromEntities(userEntityList);
	}

	@Override
	@PostMapping(value = "/")
	public EventDTO setDataFromForm(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody EventDTO data) {
		EventEntity eventEntity = new EventEntity(userModel.getById(data.getOwnerId()));
		eventEntity.convertEventDTO(data);
		eventEntity.setKitchenType(kichenTypeModel.getByName(data.getKichenType()));
		eventEntity.setHoliDay(holyDayModel.getByName(data.getHoliday()));
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
		eventModel.getById(id).putIntoDeletionQueue();
		eventModel.delete(id);
	}

	@Override
	@DeleteMapping(value = "/")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
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
}
