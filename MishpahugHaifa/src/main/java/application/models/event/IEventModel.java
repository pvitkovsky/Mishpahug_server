package application.models.event;

import java.util.HashMap;
import java.util.List;

import com.querydsl.core.types.Predicate;

public interface IEventModel {
    
    List<EventEntity> getByOwner(String ownerUserName);
    
    List<EventEntity> getByOwner(Integer ownerUserId);

    public EventEntity getById(Integer id);

    public EventEntity getByFullName(String name); //TODO: naming. full name of what?

    public Iterable<EventEntity> getAll(Predicate predicate);
    
    public EventEntity add(EventEntity data); 

    public EventEntity update(Integer eventId, HashMap<String, String> data);

    public EventEntity delete(Integer eventId);

}
