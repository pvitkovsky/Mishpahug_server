package application.utils.converter;

import java.util.HashMap;

import application.entities.EventEntity;
import application.entities.UserEntity;

public interface IUpdates {

	public void updateEvent(EventEntity eventEntity, HashMap<String, String> data); 
	
	public void updateUser(UserEntity userEntity, HashMap<String, String> data);
}
