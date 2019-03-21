package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import application.entities.LogsOnEvent.ActionsOnEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//TODO: equals and uniqueness constraint;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "userTarget")
public class LogsOnUser extends LogsDataEntity {

	//TODO: unidirectional safety check;
	@ManyToOne
	private UserEntity userTarget;

	@Column(name = "action_on_user", nullable = false)
	private ActionsOnUser action;

	public enum ActionsOnUser {
	USER_EDITION_EMAIL, USER_EDITION_ADDRESS, USER_EDITION_NAME, USER_LOGIN, USER_PROFILE_VIEW,
    USER_REGISTRATION,  USER_COMMENT
}
}
