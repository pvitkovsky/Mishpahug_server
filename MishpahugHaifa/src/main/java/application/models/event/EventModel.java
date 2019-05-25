package application.models.event;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.SubscriptionEntity.EventGuestId;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import application.repositories.*;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	@Override
	public List<EventEntity> getAll() {
		return eventRepository.findAll();
	}

	@Override
	public Set<EventEntity> getAllByUser(Integer userId) {
		UserEntity userEntity = userRepository.getOne(userId);
		Set<SubscriptionEntity> subscriptions = userEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getEvent()).collect(Collectors.toSet());
	}

	@Override
	public EventEntity add(EventEntity data) { //would throw if no user is in data's owner field;
		return eventRepository.save(data);
	}

	@Override
	public EventEntity update(Integer eventId, HashMap<String, String> data) throws ExceptionMishpaha {
		try {
			EventEntity eventEntity = eventRepository.getOne(eventId);
			//TODO
			return eventRepository.save(eventEntity);
		} catch (Exception e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
	}

	@Override
	public Set<UserEntity> getAllSubscribed(Integer eventId) {
		EventEntity eventEntity = eventRepository.getOne(eventId);
		Set<SubscriptionEntity> subscriptions = eventEntity.getSubscriptions();
		return subscriptions.stream().map(s -> s.getGuest()).collect(Collectors.toSet());
	}

	@Override
	public EventEntity delete(Integer eventId) throws ExceptionMishpaha { //throws if not in deletion queue
		try {
			EventEntity eventEntity = eventRepository.getOne(eventId);
			eventRepository.delete(eventEntity);
			return eventEntity;
		} catch (EntityNotFoundException e) {
			throw new ExceptionMishpaha("Error! Not found event with id " + eventId, null);
		}
	}

	@Override
	public void deleteAll() throws ExceptionMishpaha {
		eventRepository.deleteAll();  //throws if not in deletion queue
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
		return eventRepository.getByNameOfEvent(name);
	}

	@Override
	public Iterable<EventEntity> getAll(Predicate predicate) {
		return eventRepository.findAll(predicate);
	}

	@Override
	public EventEntity subscribe(Integer eventId, Integer userId) throws ExceptionMishpaha {
		Subscription subscription = new Subscription(eventId, userId);
		return subscription.subscribe();
	}

	@Override
	public EventEntity unsubscribe(Integer eventId, Integer userId) throws ExceptionMishpaha {
		Subscription subscription = new Subscription(eventId, userId);
		return subscription.unsubscribe();
	}

	@Override
	public EventEntity deactivateSubscription(Integer eventId, Integer userId) throws ExceptionMishpaha {
		Subscription subscription = new Subscription(eventId, userId);
		return subscription.deactivate();
	}

	/**
	 * Handles subscription logic;
	 */
	// TODO: refactor into Subscription model pls
	// TODO: integer arguments design issue; test;
	private class Subscription {
		final private Integer eventId;
		final private Integer userId;
		private EventEntity eventEntity;
		private UserEntity userEntity;
		private SubscriptionEntity subscription;

		private Subscription(Integer eventId, Integer userId) throws ExceptionMishpaha {
			this.eventId = eventId;
			this.userId = userId;
			load();
		}

		private void load() throws ExceptionMishpaha {
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
			subscription.nullifyForRemoval(); // cascaded, no need to explicitly delete;
			return eventEntity;
		}
	}
}
