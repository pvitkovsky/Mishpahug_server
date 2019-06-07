package application.models.event;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import application.exceptions.NotFoundGenderWithIDException;
import com.querydsl.core.types.Predicate;

import application.entities.EventEntity;
import application.entities.UserEntity;

public interface IEventModel {
    public List<EventEntity> getAll();

    public Set<EventEntity> getAllByUser(Integer userId); //TODO: owned or subscribed events here?

    List<EventEntity> getByOwner(String ownerUserName);

    public EventEntity add(EventEntity data); // Should not allow duplicated events;

    public EventEntity update(Integer eventId, HashMap<String, String> data);

    public Set<UserEntity> getAllSubscribed(Integer eventId);

    public EventEntity delete(Integer eventId);

    public void deleteAll();

    public EventEntity getById(Integer id);

    public EventEntity getByFullName(String name);

    public Iterable<EventEntity> getAll(Predicate predicate);

    public EventEntity subscribe(Integer eventId, Integer userId); //TODO: why integers here? looks like unnecessary representation exposure; also bad design choice: easy to put them in wrong order;

    public EventEntity unsubscribe(Integer eventId, Integer userId); //TODO: same as above;

    public EventEntity deactivateSubscription(Integer eventId, Integer userId); //TODO: same as above;

}
