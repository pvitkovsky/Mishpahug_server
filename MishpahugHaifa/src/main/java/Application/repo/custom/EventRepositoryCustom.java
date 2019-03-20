package application.repo.custom;

import application.entities.EventEntity;

import java.util.HashMap;
import java.util.List;

public interface EventRepositoryCustom {
    public List<EventEntity> searchByFilter(HashMap<String, String> filter);
}
