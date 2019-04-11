package application.repositories.custom;

import application.entities.EventEntity;

import java.util.Map;

public interface EventRepositoryCustom {
   
	public EventEntity update(EventEntity event, Map<String, String> data);
    
}
