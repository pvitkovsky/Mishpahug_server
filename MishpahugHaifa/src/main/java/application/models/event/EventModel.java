package application.models.event;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.models.event.commands.EventDeleted;
import application.models.user.commands.UserDeleted;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import application.utils.converter.IConverter;
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
	IConverter<EventEntity, EventDTO> converterEvent;
	
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
	public List<EventEntity> getByOwner(String ownerUserName) { 
		return eventRepository.getByUserEntityOwner_UserName(ownerUserName);
	}
	
	@Override
	public List<EventEntity> getByOwner(Integer ownerUserId) {
		return eventRepository.getByUserEntityOwner_Id(ownerUserId);
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

	@Override
	public EventEntity add(EventEntity data) { 
		Optional<EventEntity> savedEvent = eventRepository.findByDateAndTimeAndUserEntityOwner(data.getDate(), data.getTime(), data.getUserEntityOwner());
		return savedEvent.orElseGet(() -> eventRepository.save(data));
	}

	@Override
	public EventEntity update(Integer eventId, HashMap<String, String> data){ 
		EventEntity eventEntity = eventRepository.getOne(eventId);
		converterEvent.updateEntity(eventEntity, data);
		return eventEntity;
	}

	@Override
	public EventEntity delete(Integer eventId){ 
		
		EventDeleted eventDeleted = new EventDeleted(eventId); 
		applicationEventPublisher.publishEvent(eventDeleted);
			
		EventEntity eventEntity = eventRepository.getOne(eventId);
		eventEntity.putIntoDeletionQueue();
		
		eventRepository.delete(eventEntity);
		return eventEntity;
	}




	

}
