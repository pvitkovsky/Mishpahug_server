package Application.entities.values;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor

@Embeddable
public class LogsDataValue {

	//TODO: Bidirectional private UserEntity userItemOwner;
	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "action")
	private UserActions action;

	@Column(name = "description")
	private String description;

	public enum UserActions {
		EVENT_STATUS_CHANGE, USER_EDITION_EMAIL, USER_EDITION_ADDRESS, USER_EDITION_NAME, USER_LOGIN, USER_PROFILE_VIEW,
        USER_REGISTRATION, EVENT_SUBSCRIBE, EVENT_UNSUBSCRIBE, EVENT_EDITION, EVENT_VIEW, EVENT_CANCEL, EVENT_COMMENT,
        USER_COMMENT
	}

	public LogsDataValue() {
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public UserActions getAction() {
		return action;
	}

	public void setAction(UserActions action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LogsDataValue(LocalDate date, LocalTime time, UserActions action, String description) {
		this.date = date;
		this.time = time;
		this.action = action;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LogsDataValue that = (LogsDataValue) o;
		return date.equals(that.date) &&
				time.equals(that.time) &&
				action == that.action &&
				description.equals(that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, time, action, description);
	}
}
