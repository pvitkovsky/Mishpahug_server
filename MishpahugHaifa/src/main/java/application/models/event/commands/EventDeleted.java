package application.models.event.commands;

import java.time.Instant;

public class EventDeleted {

	private Integer eventId;
	private Instant timestamp = Instant.now();

	public EventDeleted(Integer eventId) {
		super();
		this.eventId = eventId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
	
}
