package application.repo.custom;


import java.util.HashMap;
import java.util.List;

import application.entities.EventEntity;
import application.entities.UserEntity;

public interface EventRepositoryCustom {
    public List<EventEntity> searchByFilter(HashMap<String, String> filter);
   
}
