package application.models.user.commands;

import java.time.Instant;

import application.models.user.UserEntity.UserStatus;

public class UserChanged {

	private Integer userId;
	private UserStatus newStatus;
	private Instant timestamp = Instant.now();

	public UserChanged(Integer userId, UserStatus newStatus) {
		super();
		this.userId = userId;
		this.newStatus = newStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public UserStatus getNewStatus() {
		return newStatus;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}
