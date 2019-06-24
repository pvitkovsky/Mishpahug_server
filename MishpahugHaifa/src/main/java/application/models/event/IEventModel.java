package application.models.event;

import application.models.user.UserEntity;

import com.querydsl.core.types.Predicate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface IEventModel {
    
    List<EventEntity> getByOwner(Integer ownerUserId);

    Integer size();

    public EventEntity add(EventEntity data); // Should not allow duplicated events;

    public EventEntity update(Integer eventId, HashMap<String, String> data);


    public EventEntity delete(Integer eventId);

    public void deleteAll();

    public EventEntity getById(Integer id);

    public EventEntity getByFullName(String name);

    public Iterable<EventEntity> getAll(Predicate predicate);



}
