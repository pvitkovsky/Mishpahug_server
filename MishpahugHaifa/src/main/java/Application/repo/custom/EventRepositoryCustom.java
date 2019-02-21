package Application.repo.custom;

import Application.entities.EventEntity;

import java.util.HashMap;
import java.util.List;

public interface EventRepositoryCustom {
    public List<EventEntity> searchByFilter(HashMap<String, String> filter);
}
