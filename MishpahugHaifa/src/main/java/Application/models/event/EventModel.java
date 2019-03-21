package application.models.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import application.entities.EventEntity;
import application.entities.EventGuestRelation;
import application.entities.EventGuestRelation.EventGuestId;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import application.repo.EventGuestRepository;
import application.repo.EventRepository;
import application.repo.UserRepository;

@Service
@Transactional
public class EventModel implements IEventModel {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventGuestRepository subscriptionsRepository;

	@Override
	public List<EventEntity> getAll() {
		return eventRepository.findAll();
	}

	@Override
	/**
	 * Guest
	 */
	public Set<EventEntity> getAllByUser(Integer userId) {
		UserEntity userEntity = userRepository.getOne(userId);
		Set<EventGuestRelation> subscriptions = userEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getEvent()).collect(Collectors.toSet());
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
	public EventEntity update(Integer eventId, HashMap<String, String> data)
	{
		EventEntity eventEntity = eventRepository.getOne(eventId);
		if (data.containsKey("date")) eventEntity.setDate(LocalDate.parse(data.get("date")));
		if (data.containsKey("time")) eventEntity.setTime(LocalTime.parse(data.get("time")));
		if (data.containsKey("nameofevent")) eventEntity.setNameOfEvent(data.get("nameofevent"));
		/*!!!*/
		if (data.containsKey("address.build"))
			eventEntity.getAddressEntity().setBuilding(Integer.getInteger(data.get("address.build")));
		if (data.containsKey("address.apartment"))
			eventEntity.getAddressEntity().setApartment(Integer.getInteger(data.get("address.apartment")));
		if (data.containsKey("address.street"))
			eventEntity.getAddressEntity().setStreet(data.get("address.street"));
		eventRepository.save(eventEntity);
		return eventEntity;
	}

	@Override
	public Set<UserEntity> getAllSubscribed(Integer eventId) {
		EventEntity eventEntity = eventRepository.getOne(eventId);
		Set<EventGuestRelation> subscriptions = eventEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getUserGuest()).collect(Collectors.toSet());
	}

	@Override
	public EventEntity remove(Integer eventId) throws ExceptionMishpaha {
		try {
			EventEntity eventEntity = eventRepository.getOne(eventId);
			UserEntity userOwner = eventEntity.getUserEntityOwner();
			userOwner.removeOwnedEvent(eventEntity);
			userRepository.save(userOwner); 
			//TODO: check cascade; 
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
		EventGuestRelation subscription = new EventGuestRelation();
		subscription.subscribe(userEntity, eventEntity);
		return eventEntity;
	}

	@Override
	public EventEntity unsubscribe(Integer eventId, Integer userId) throws ExceptionMishpaha {
		EventEntity eventEntity;
		UserEntity userEntity;
		EventGuestRelation subscription;
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
		EventGuestId subscriptionKey = new EventGuestId(userEntity.getId(), eventEntity.getId());
		if(!subscriptionsRepository.existsById(subscriptionKey)) {
			throw new ExceptionMishpaha("User with id " + userId + " is not subscribed to event with id " + eventId , null);
		}			
		subscription = subscriptionsRepository.getOne(subscriptionKey);
		if (!userEntity.getSubscriptions().contains(subscription) || !eventEntity.getSubscriptions().contains(subscription)){
			throw new IllegalStateException("User is guest of event, but his set of subscriptions does not contain this event");
		}
		subscription.unsubscribe(userEntity, eventEntity); //cascaded, no need to explicitly delete; 
		return eventEntity;
	}
}
