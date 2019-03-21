package application.repo.custom;

import application.entities.EventEntity;

import java.util.HashMap;
import java.util.List;

public interface EventRepositoryCustom {
    public EventEntity update(Integer userId, HashMap<String, String> data);
    public List<EventEntity> searchByFilter(HashMap<String, String> filter);
}
