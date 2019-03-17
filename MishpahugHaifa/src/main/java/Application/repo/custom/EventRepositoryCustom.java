package Application.repo.custom;

import java.util.HashMap;
import java.util.List;

import Application.entities.EventEntity;
import Application.entities.UserEntity;

public interface EventRepositoryCustom {
    public List<EventEntity> searchByFilter(HashMap<String, String> filter);
   
}
