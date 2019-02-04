package Application.models.event;

import Application.entities.EventItem;

import java.util.List;
import java.util.HashMap;

public interface IEventModel {
    public List<EventItem> getAll();
    public List<EventItem> getAllByUser(Integer userId);
    public List<EventItem> getWithFilter(HashMap<String, String> filter);
    public EventItem addEvent(EventItem data);
    public EventItem updateEvent(Integer eventId, HashMap<String, String> data);
}
