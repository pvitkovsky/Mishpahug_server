package Application.models.event;

import Application.entities.EventEntity;
import Application.entities.UserEntity;
import Application.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.HashMap;

public class EventModel implements IEventModel{

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<EventEntity> getAll() {
        return null;
    }

    @Override
    public List<EventEntity> getAllByUser(Integer userId) {
        return null;
    }

    @Override
    public List<EventEntity> getWithFilter(HashMap<String, String> filter) {
        return null;
    }

    @Override
    public EventEntity addEvent(EventEntity data) {
        return null;
    }

    @Override
    public EventEntity updateEvent(Integer eventId, HashMap<String, String> data) {
        return null;
    }

    @Override
    public List<UserEntity> getAllSubscribed(Integer eventId) {
        return null;
    }

    @Override
    public EventEntity removeEvent(Integer eventId) {
        return null;
    }
}
