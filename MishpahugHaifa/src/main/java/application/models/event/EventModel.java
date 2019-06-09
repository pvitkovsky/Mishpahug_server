package application.models.event;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import application.exceptions.EntityExistsException;
import application.exceptions.NotFoundEntityException;
import application.utils.converter.IUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.SubscriptionEntity.EventGuestId;
import application.entities.UserEntity;
import application.repositories.EventRepository;
import application.repositories.HolyDayRepository;
import application.repositories.KichenTypeRepository;
import application.repositories.ReligionRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;

@Service
@Transactional
public class EventModel implements IEventModel {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SubscriptionRepository subscriptionsRepository;
	@Autowired
	ReligionRepository religionRepository;
	@Autowired
	KichenTypeRepository kichenTypeRepository;
	@Autowired
	HolyDayRepository holyDayRepository;

	@Autowired
	IUpdates updates;

	@Override
	public List<EventEntity> getAll() {
		return eventRepository.findAll();
	}

	// TODO: owned events by user; owner by event;

	@Override
	public Set<EventEntity> getAllByUser(Integer userId) {// TODO: rename to getSubscriptionsByUser
		if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
		UserEntity userEntity = userRepository.getOne(userId);
		Set<SubscriptionEntity> subscriptions = userEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getEvent()).collect(Collectors.toSet());
	}

	@Override
	public Set<UserEntity> getAllSubscribed(Integer eventId) {// TODO: rename to get GuestsByEvent
		if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
		EventEntity eventEntity = eventRepository.getOne(eventId);
		Set<SubscriptionEntity> subscriptions = eventEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getGuest()).collect(Collectors.toSet());
	}

	@Override
	public List<EventEntity> getByOwner(String ownerUserName) {
		return eventRepository.getByUserEntityOwner_UserName(ownerUserName);
	}

	@Override
	public EventEntity add(EventEntity data) { // would throw if no user is in data's owner field;
		if (eventRepository.existsByDateAndTimeAndUserEntityOwner(data.getDate(),data.getTime(), data.getUserEntityOwner()))
			throw new EntityExistsException("");
		return eventRepository.save(data);
	}

	@Override
	public EventEntity update(Integer eventId, HashMap<String, String> data){
		if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
		EventEntity eventEntity = eventRepository.getOne(eventId);
		updates.updateEvent(eventEntity, data);
		return eventRepository.save(eventEntity);
	}

	@Override
	public EventEntity delete(Integer eventId){ // throws if not in deletion queue
		if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
		EventEntity eventEntity = eventRepository.getOne(eventId);
		eventRepository.delete(eventEntity);
		return eventEntity;
	}

	@Override
	public void deleteAll(){
		eventRepository.deleteAll(); // throws if not in deletion queue
	}

	@Override
	public EventEntity getById(Integer eventId){
		if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
		return eventRepository.getOne(eventId);
	}

	@Override
	public EventEntity getByFullName(String name) {
		return eventRepository.getByNameOfEvent(name);
	}

	@Override
	public Iterable<EventEntity> getAll(Predicate predicate) {
		return eventRepository.findAll(predicate);
	}

	@Override
	public EventEntity subscribe(Integer eventId, Integer userId){
		if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
		if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
		SubscriptionHandler handler = new SubscriptionHandler(eventId, userId);
		return handler.subscribe();
	}

	@Override
	public EventEntity unsubscribe(Integer eventId, Integer userId){
		if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
		if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
		SubscriptionHandler handler = new SubscriptionHandler(eventId, userId);
		return handler.unsubscribe();
	}

	@Override
	public EventEntity deactivateSubscription(Integer eventId, Integer userId){
		if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
		if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
		SubscriptionHandler handler = new SubscriptionHandler(eventId, userId);
		return handler.deactivate();
	}

	/**
	 * Handles subscription logic;
	 */
	// TODO: refactor into Subscription model pls
	// TODO: integer arguments design issue; test;
	private class SubscriptionHandler {
		final private Integer eventId;
		final private Integer userId;
		private EventEntity eventEntity;
		private UserEntity userEntity;
		private SubscriptionEntity subscription;

		private SubscriptionHandler(Integer eventId, Integer userId){
			this.eventId = eventId;
			this.userId = userId;
			load();
		}

		private void load(){
			if (!eventRepository.existsById(eventId)) throw new NotFoundEntityException("");
			if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
			eventEntity = eventRepository.getOne(eventId);
			userEntity = userRepository.getOne(userId);
			EventGuestId subscriptionKey = new EventGuestId(userEntity.getId(), eventEntity.getId());
			if (!subscriptionsRepository.existsById(subscriptionKey)) {
				subscription = new SubscriptionEntity();
			} else {
				subscription = subscriptionsRepository.getOne(subscriptionKey);
				if (!userEntity.getSubscriptions().contains(subscription)
						|| !eventEntity.getSubscriptions().contains(subscription)) {
					throw new IllegalStateException(
							"Subscription relation exists, but is not in subscription sets of Event or Guest");
				}
			}
		}

		EventEntity subscribe() {
			return eventEntity;
		}

		EventEntity cancel() {
			subscription.cancel();
			return eventEntity;
		}

		EventEntity deactivate() {
			subscription.deactivate();
			return eventEntity;
		}

		EventEntity unsubscribe() {
			subscription.putIntoDeletionQueue();
			subscription.nullifyForRemoval(); // cascaded, no need to explicitly delete;
			return eventEntity;
		}
	}
}
