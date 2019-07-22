package application.models.event.commands;

import java.time.Instant;

import application.models.event.EventEntity.EventStatus;

public class EventChanged {
	
	private Integer eventId;
    private EventStatus newStatus;
    private Instant timestamp = Instant.now();
    
	public EventChanged(Integer eventId, EventStatus newStatus) {
		super();
		this.eventId = eventId;
		this.newStatus = newStatus;
	}

	public Integer getEventId() {
		return eventId;
	}
	
	public EventStatus getNewStatus() {
		return newStatus;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}
