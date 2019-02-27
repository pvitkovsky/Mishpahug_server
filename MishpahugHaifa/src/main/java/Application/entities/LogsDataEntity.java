package Application.entities;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "logs")
public class LogsDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "usernickname", nullable = false)
	private String userNickName;
	
	@Column(name = "eventdesc", nullable = true)
	private String eventDescription;
	
	@Column(name = "date", nullable = false)
	private LocalDate date;

	@Column(name = "time", nullable = false)
	private LocalTime time;

	@Column(name = "action", nullable = false)
	private UserActions action;

	@Column(name = "description")
	private String description;

	public enum UserActions {
		EVENT_STATUS_CHANGE, USER_EDITION_EMAIL, USER_EDITION_ADDRESS, USER_EDITION_NAME, USER_LOGIN, USER_PROFILE_VIEW,
        USER_REGISTRATION, EVENT_SUBSCRIBE, EVENT_UNSUBSCRIBE, EVENT_EDITION, EVENT_VIEW, EVENT_CANCEL, EVENT_COMMENT,
        USER_COMMENT
	}

	@Override
	public String toString() {
		return "LogsDataEntity{" +
				"id=" + id +
				", userNickName='" + userNickName + '\'' +
				", eventDescription='" + eventDescription + '\'' +
				", date=" + date +
				", time=" + time +
				", action=" + action +
				", description='" + description + '\'' +
				'}';
	}

	public LogsDataEntity() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LogsDataEntity that = (LogsDataEntity) o;
		return userNickName.equals(that.userNickName) &&
				eventDescription.equals(that.eventDescription) &&
				date.equals(that.date) &&
				time.equals(that.time) &&
				action == that.action;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userNickName, eventDescription, date, time, action);
	}

	public LogsDataEntity(String userNickName, String eventDescription, LocalDate date, LocalTime time, UserActions action, String description) {
		this.userNickName = userNickName;
		this.eventDescription = eventDescription;
		this.date = date;
		this.time = time;
		this.action = action;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
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
}
