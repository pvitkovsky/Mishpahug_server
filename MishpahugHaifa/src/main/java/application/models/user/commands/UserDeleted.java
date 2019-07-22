package application.models.user.commands;

import java.time.Instant;

public class UserDeleted {

	private Integer userId;
	private Instant timestamp = Instant.now();

	public UserDeleted(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
	
}
