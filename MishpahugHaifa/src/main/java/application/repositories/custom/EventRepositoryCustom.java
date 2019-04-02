package application.repositories.custom;

import application.entities.EventEntity;

import java.util.HashMap;
import java.util.List;

public interface EventRepositoryCustom {
    public EventEntity update(EventEntity event, HashMap<String, String> data);
}
