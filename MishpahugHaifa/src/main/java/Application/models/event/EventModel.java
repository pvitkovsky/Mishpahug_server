package Application.models.event;

import Application.entities.EventEntity;
import Application.entities.UserEntity;
import Application.exceptions.ExceptionMishpaha;
import Application.repo.EventRepository;
import Application.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Set;

@Service
public class EventModel implements IEventModel{

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
        return userRepository.getOne(userId).getEventItemsGuest();
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
        return eventRepository.getOne(eventId).getUserItemsGuestsOfEvents();
    }

    @Override
    public EventEntity remove(Integer eventId) {
        EventEntity eventEntity = eventRepository.getOne(eventId);
        eventRepository.deleteById(eventId);
        return eventEntity;
    }

    @Override
    public EventEntity getById(Integer id) {
        EventEntity eventEntity = eventRepository.getOne(id);
        if (eventEntity != null) return eventEntity;
        else {
            new ExceptionMishpaha("Error! Not found event", null);
            return null;
        }
    }

    @Override
    public EventEntity getByFullName(String name) {
        return eventRepository.byFullName(name);
    }

    @Override
    public EventEntity subscribe(Integer eventId, Integer userId) {
        EventEntity eventEntity = eventRepository.getOne(eventId);
        UserEntity userEntity = userRepository.getOne(userId);
        if ((userEntity != null) && (eventEntity != null)){
            eventEntity.subscribe(userEntity);
            eventRepository.save(eventEntity);
            userEntity.getEventItemsGuest().add(eventEntity);
            userRepository.save(userEntity);
        }
        else new ExceptionMishpaha("Error! Not found user or event", null);
        return eventEntity;
    }

    @Override
    public EventEntity unsubscribe(Integer eventId, Integer userId) {
        EventEntity eventEntity = eventRepository.getOne(eventId);
        UserEntity userEntity = userRepository.getOne(userId);
        if ((userEntity != null) && (eventEntity != null)){
            eventEntity.unsubscribe(userEntity);
            eventRepository.save(eventEntity);
            userEntity.getEventItemsGuest().add(eventEntity);
            userRepository.save(userEntity);
        }
        else new ExceptionMishpaha("Error! Not found user or event", null);
        return eventEntity;
    }
}
