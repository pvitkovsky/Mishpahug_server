package application.repositories.custom;

import java.util.Map;

import application.entities.EventEntity;

public interface EventRepositoryCustom {
   
	public EventEntity update(EventEntity event, Map<String, String> data);
    
//	/**
//	 * As EventEntity is weak entity, it must be deleted from the user; 
//	 * @param event
//	 */
//    public void delete(EventEntity event);
    
}
