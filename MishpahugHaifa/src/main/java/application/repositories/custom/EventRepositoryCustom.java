package application.repositories.custom;

import application.entities.EventEntity;

import java.util.Map;

public interface EventRepositoryCustom {

    public EventEntity update(EventEntity event, Map<String, String> data);

    /**
     * As EventEntity is weak entity, it must be deleted from the user;
     *
     * @param event
     */
    public void delete(EventEntity event);

}
