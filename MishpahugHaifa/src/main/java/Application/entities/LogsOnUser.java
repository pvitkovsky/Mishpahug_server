package Application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
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
