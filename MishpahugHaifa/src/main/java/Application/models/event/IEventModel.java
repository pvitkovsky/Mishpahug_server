package Application.models.event;

import Application.entities.EventEntity;
import Application.entities.UserEntity;

import java.util.List;
import java.util.HashMap;

public interface IEventModel {
    public List<EventEntity> getAll();
    public List<EventEntity> getAllByUser(Integer userId);
    public List<EventEntity> getByFilter(HashMap<String, String> filter);
    public EventEntity add(EventEntity data); // Should not allow duplicated events; 
    public EventEntity update(Integer eventId, HashMap<String, String> data);
    public List<UserEntity> getAllSubscribed(Integer eventId);
    public EventEntity remove(Integer eventId);
    public EventEntity getById(Integer id);
}
