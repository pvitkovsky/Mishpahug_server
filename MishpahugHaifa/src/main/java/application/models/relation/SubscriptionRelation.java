package application.models.relation;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import application.models.event.EventEntity;
import application.models.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AttributeOverrides({
    @AttributeOverride(name = "guestId", column = @Column(name = "GUEST_ID")),
    @AttributeOverride(name = "eventId", column = @Column(name = "EVENT_ID"))
})
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode(of = { "userGuestId", "eventId" })
@ToString
@Getter
public class SubscriptionRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Integer eventId;
	
	@Column
	private Integer guestId; 

	public SubscriptionRelation(Integer eventId, Integer userGuestId) { //TODO maybe pair type? or conve
		super();
		this.eventId = eventId;
		this.guestId = userGuestId;
	}

}
