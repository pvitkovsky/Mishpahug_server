package application.utils.converter;

import application.models.event.EventEntity;
import application.models.user.UserEntity;

import java.util.HashMap;

public interface IUpdates {

	public void updateEvent(EventEntity eventEntity, HashMap<String, String> data); 
	
	public void updateUser(UserEntity userEntity, HashMap<String, String> data);
}
