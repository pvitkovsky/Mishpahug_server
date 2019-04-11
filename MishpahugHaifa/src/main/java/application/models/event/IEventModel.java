package application.models.event;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface IEventModel {
    public List<EventEntity> getAll();

    public Set<EventEntity> getAllByUser(Integer userId); //TODO: owned or subscribed events here?

    public EventEntity add(EventEntity data); // Should not allow duplicated events;

    public EventEntity update(Integer eventId, HashMap<String, String> data) throws ExceptionMishpaha;

    public Set<UserEntity> getAllSubscribed(Integer eventId);

    public EventEntity delete(Integer eventId) throws ExceptionMishpaha;

    public void deleteAll() throws ExceptionMishpaha;

    public EventEntity getById(Integer id) throws ExceptionMishpaha;

    public EventEntity getByFullName(String name);

    public Iterable<EventEntity> getAll(Predicate predicate);

    public EventEntity subscribe(Integer eventId, Integer userId) throws ExceptionMishpaha; //TODO: why integers here? looks like unnecessary representation exposure; also bad design choice: easy to put them in wrong order;

    public EventEntity unsubscribe(Integer eventId, Integer userId) throws ExceptionMishpaha; //TODO: same as above;

    public EventEntity deactivateSubscription(Integer eventId, Integer userId) throws ExceptionMishpaha; //TODO: same as above;

}
