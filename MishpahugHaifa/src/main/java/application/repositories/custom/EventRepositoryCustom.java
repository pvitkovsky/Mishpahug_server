package application.repositories.custom;

import application.entities.EventEntity;

import java.util.HashMap;

public interface EventRepositoryCustom {
    public EventEntity update(EventEntity event, HashMap<String, String> data);
}
