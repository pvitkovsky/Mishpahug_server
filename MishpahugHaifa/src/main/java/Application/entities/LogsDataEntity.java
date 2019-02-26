package Application.entities;


import java.time.LocalDate;
import java.time.LocalTime;

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

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "logs")
public class LogsDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "usernickname")//Mandatory
	private String userNickName;
	
	@Column(name = "EventID") //Optional date-time-event_name; 
	private String eventDescription;
	
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

}
