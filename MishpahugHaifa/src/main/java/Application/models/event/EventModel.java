package application.models.event;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import application.repo.EventRepository;
import application.repo.UserRepository;

@Service
@Transactional
public class EventModel implements IEventModel {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<EventEntity> getAll() {
		return eventRepository.findAll();
	}

	@Override
	public Set<EventEntity> getAllByUser(Integer userId) {
		return userRepository.getOne(userId).getEventEntityGuest();
	}

	@Override
	public List<EventEntity> getByFilter(HashMap<String, String> filter) {
		return eventRepository.searchByFilter(filter);

	}

	@Override
	public EventEntity add(EventEntity data) {
		return eventRepository.save(data);

	}

	@Override
	public EventEntity update(Integer eventId, HashMap<String, String> data){
		return eventRepository.save(eventRepository.update(eventId, data));
	}

	@Override
	public Set<UserEntity> getAllSubscribed(Integer eventId) {
		return eventRepository.getOne(eventId).getUserItemsGuestsOfEvents();
	}

	@Override
	public EventEntity remove(Integer eventId) throws ExceptionMishpaha {
		try {
			EventEntity eventEntity = eventRepository.getOne(eventId);
			UserEntity userOwner = eventEntity.getUserEntityOwner();
			userOwner.removeOwnedEvent(eventEntity);
			for (UserEntity guest : eventEntity.getUserItemsGuestsOfEvents()) {
				eventEntity.unSubscribe(guest);
			}
			userRepository.save(userOwner); // event is cascaded from user;
			return eventEntity;
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
	}

	@Override
	public EventEntity getById(Integer id) throws ExceptionMishpaha {
		try {
			return eventRepository.getOne(id);
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + id, null);
		}
	}

	@Override
	public EventEntity getByFullName(String name) {
		return eventRepository.byFullName(name);

	}

	@Override
	public EventEntity subscribe(Integer eventId, Integer userId) throws ExceptionMishpaha {
		EventEntity eventEntity;
		UserEntity userEntity;
		try {
			eventEntity = eventRepository.getOne(eventId);
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
		try {
			userEntity = userRepository.getOne(userId);
		} catch (Exception e) {
			throw new ExceptionMishpaha("Error! Not found user with id " + userId, null);
		}
		eventEntity.subscribe(userEntity);
		userRepository.save(userEntity);
		return eventEntity;
	}

	@Override
	public EventEntity unsubscribe(Integer eventId, Integer userId) throws ExceptionMishpaha {
		EventEntity eventEntity;
		UserEntity userEntity;
		try {
			eventEntity = eventRepository.getOne(eventId);
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
		try {
			userEntity = userRepository.getOne(userId);
		} catch (Exception e) {
			throw new ExceptionMishpaha("Error! Not found user with id " + userId, null);
		}
		eventEntity.unSubscribe(userEntity);
		userRepository.save(userEntity);
		return eventEntity;
	}
}
