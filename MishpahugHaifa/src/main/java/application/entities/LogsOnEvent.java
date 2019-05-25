package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
@ToString(exclude = "eventTarget")
public class LogsOnEvent extends LogsDataEntity {

    //TODO: unidirectional safety check;
    @ManyToOne
    private EventEntity eventTarget;

    @Column(name = "action_on_event", nullable = false)
    private ActionsOnEvent action;

    public enum ActionsOnEvent {
        EVENT_VIEW, EVENT_SUBSCRIBE, EVENT_UNSUBSCRIBE, EVENT_STATUS_CHANGE, EVENT_CANCEL, EVENT_COMMENT,
    }
}
