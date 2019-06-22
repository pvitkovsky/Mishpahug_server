package application.models.event;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.models.user.UserDeleted;
import application.models.user.UserEntity;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import application.utils.converter.IUpdates;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EventModel implements IEventModel {


	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SubscriptionRepository subscriptionsRepository;
	
	@Autowired
	IUpdates updates;
	
    @EventListener
    public void updateEventsOnUserDelete(UserDeleted userDeleted) {
        	log.warn("EventModel -> userChanged event deleted detected");
        	List<EventEntity> eventsToDelete = eventRepository.getByUserEntityOwner_Id(userDeleted.getUserId());
        	log.warn("Events To Delete: " + eventsToDelete );
        	eventsToDelete.forEach(event -> { 
        		log.warn("  event deletion: " + event );
        		event.putIntoDeletionQueue();
    			EventDeleted eventDeleted = new EventDeleted(event.getId());
    			applicationEventPublisher.publishEvent(eventDeleted);
    		});
        	eventRepository.deleteAll(eventsToDelete);
        	log.warn("Check eventModelUserDelete " + eventRepository.findAll());
    }


	@Override
	public List<EventEntity> getSubscribedEvents(Integer userId) { /*inter-aggregate query*/
		UserEntity userEntity = userRepository.getOne(userId);
		return subscriptionsRepository.getEventsForGuest(userEntity);
	}

	@Override
	public List<UserEntity> getSubscribedGuests(Integer eventId) {  /*inter-aggregate query*/
		EventEntity eventEntity = eventRepository.getOne(eventId);
		return subscriptionsRepository.getGuestsForEvent(eventEntity);
	}

	@Override
	public List<EventEntity> getByOwner(String ownerUserName) { /*inter-aggregate query*/
		return eventRepository.getByUserEntityOwner_UserName(ownerUserName);
	}
	
	@Override
	public List<EventEntity> getByOwner(Integer ownerUserId) { /*inter-aggregate query*/
		return eventRepository.getByUserEntityOwner_Id(ownerUserId);
	}

	@Override
	public Integer size() { // would throw if no user is in data's owner field;
		return eventRepository.findAll().size();
	}

	@Override
	public EventEntity add(EventEntity data) { // would throw if no user is in data's owner field; //TODO: event emitter
		return eventRepository.save(data);
	}

	@Override
	public EventEntity update(Integer eventId, HashMap<String, String> data){ //TODO: event emitter
		EventEntity eventEntity = eventRepository.getOne(eventId);
		updates.updateEvent(eventEntity, data);
		return eventEntity;
	}

	@Override
	public EventEntity delete(Integer eventId){ // throws if not in deletion queue
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


	

}
