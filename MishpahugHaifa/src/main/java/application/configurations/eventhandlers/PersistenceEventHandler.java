package application.configurations.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import application.models.event.EventChanged;
import application.models.event.EventDeleted;
import application.models.event.EventEntity;
import application.models.user.UserChanged;
import application.models.user.UserDeleted;
import application.models.user.UserEntity;

@RepositoryEventHandler
public class PersistenceEventHandler {
	
	 @Autowired
	 private ApplicationEventPublisher applicationEventPublisher;
	 
	 @HandleBeforeSave
	  public void handleSave(UserEntity user) {
		 System.out.println("handle");
		 UserChanged userChanged = new UserChanged(user.getId(), user.getStatus());
		 applicationEventPublisher.publishEvent(userChanged);
	  }
	 
	 @HandleBeforeSave
	  public void handleSave(EventEntity event) {
		 System.out.println("handle");
		 EventChanged eventChanged = new EventChanged(event.getId(), event.getStatus());
		 applicationEventPublisher.publishEvent(eventChanged);
	  }
	 
	 @HandleBeforeDelete
	  public void handleDelete(UserEntity user) {
		 System.out.println("handle");
		 UserDeleted userDeleted = new UserDeleted(user.getId());
		 applicationEventPublisher.publishEvent(userDeleted);
	  }
	 
	 @HandleBeforeDelete
	  public void handleDelete(EventEntity event) {
		 System.out.println("handle");
		 EventDeleted eventDeleted = new EventDeleted(event.getId());
		 applicationEventPublisher.publishEvent(eventDeleted);
	  }
	 
	 

}
