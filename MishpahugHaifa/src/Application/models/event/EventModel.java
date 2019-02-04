package Application.models.event;

import Application.entities.EventItem;
import Application.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.HashMap;

public class EventModel implements IEventModel{

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<EventItem> getAll() {
        return null;
    }

    @Override
    public List<EventItem> getAllByUser(Integer userId) {
        return null;
    }

    @Override
    public List<EventItem> getWithFilter(HashMap<String, String> filter) {
        return null;
    }

    @Override
    public EventItem addEvent(EventItem data) {
        return null;
    }

    @Override
    public EventItem updateEvent(Integer eventId, HashMap<String, String> data) {
        return null;
    }
}
