package Application.models.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;
import Application.exceptions.ExceptionMishpaha;
import Application.repo.EventGuestRepository;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

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
	public Set<EventEntity> getAllByUser(Integer userId) {
		UserEntity guest = userRepository.getOne(userId);
		Set<EventGuestRelation> subscriptions = subscriptionsRepository.findAllByUser(guest);
		return eventRepository.findEventBySubscriptions(subscriptions);
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
	public EventEntity update(Integer eventId, HashMap<String, String> data) {
		return null;
	}

	@Override
	public Set<UserEntity> getAllSubscribed(Integer eventId) {
		EventEntity event = eventRepository.getOne(eventId);
		Set<EventGuestRelation> subsriptions = event.getUserItemsGuestsOfEvents();
		return userRepository.findUserBySubscriptions(subsriptions);
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
		subscriptionsRepository.save(subscription);
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
		try {
			subscription = subscriptionsRepository.findByUserGuestAndEvent(userEntity, eventEntity);
		} catch (Exception e) {
			throw new ExceptionMishpaha("User with id " + userId + " is not subscribed to event with id " + eventId , null);
		}
		
		

//		if (false) { // not found subscription with this key
//			throw new IllegalArgumentException("Not subscribed and trying to unsubscribe");
//		}
//		if (false) {// subscription found && (user.does_not_contain || event.doesnotcontain)))
//			throw new IllegalStateException("User is guest of event, but his set of subscriptions does not contain this event");
//		}
		subscription.unsubscribe(userEntity, eventEntity);
		subscriptionsRepository.delete(subscription);

		
		return eventEntity;
	}
}
