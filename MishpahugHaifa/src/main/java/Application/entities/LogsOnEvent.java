package Application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LogsOnEvent extends LogsDataEntity {

	//TODO: unidirectional safety check;
	@ManyToOne
	private EventEntity eventTarget;

	@Column(name = "action_on_event", nullable = false)
	private ActionsOnEvent action;

	public enum ActionsOnEvent {
		EVENT_SUBSCRIBE, EVENT_UNSUBSCRIBE, EVENT_EDITION, EVENT_VIEW, EVENT_CANCEL, EVENT_COMMENT,
	}
}
