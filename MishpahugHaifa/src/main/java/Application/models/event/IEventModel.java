package Application.models.event;

import Application.entities.EventEntity;
import Application.entities.UserEntity;

import java.util.List;
import java.util.HashMap;

public interface IEventModel {
    public List<EventEntity> getAll();
    public List<EventEntity> getAllByUser(Integer userId);
    public List<EventEntity> getWithFilter(HashMap<String, String> filter);
    public EventEntity addEvent(EventEntity data);
    public EventEntity updateEvent(Integer eventId, HashMap<String, String> data);
    public List<UserEntity> getAllSubscribed(Integer eventId);
    public EventEntity removeEvent(Integer eventId);
}
