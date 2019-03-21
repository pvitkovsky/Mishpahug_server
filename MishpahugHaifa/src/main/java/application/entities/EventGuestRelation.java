package application.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import application.entities.values.FeedBackValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
/*
 * No @AllArgsConstructor because there's an embedded composite ID whose fields must be set manually; 
 */
@NoArgsConstructor
@EqualsAndHashCode (of = {"userGuest", "event"})
@Entity
@Table(name = "user_event_guest", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_guest", "event_sub"}) })
@ToString
public class EventGuestRelation {

	@Embeddable
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	@ToString
	public static class EventGuestId implements Serializable {
		
		private static final long serialVersionUID = 1L;

		@Column(name = "GUEST_ID")
		protected Integer userGuestId;
		
		@Column(name = "EVENT_ID")
		protected Integer eventId;	
		
		
	}
	
	@EmbeddedId
	protected EventGuestId id = new EventGuestId();
	

	@ManyToOne //TODO: cascading
	@JoinColumn(name = "user_guest", insertable = false, updatable = false)
	@Setter(AccessLevel.PACKAGE)
	private UserEntity userGuest;

	@ManyToOne //TODO: cascading
	@JoinColumn(name = "event_sub", insertable = false, updatable = false)
	@Setter(AccessLevel.PACKAGE)
	private EventEntity event;
	
	@Embedded
	private FeedBackValue feedback;
	
	
	public EventGuestRelation(UserEntity userGuest, EventEntity event) {
		super();
		setRelationAndID(userGuest,  event);
	}
	
	public EventGuestRelation(EventGuestId id, UserEntity userGuest, EventEntity event, FeedBackValue feedback) {
		super();
		this.id = id;
		setRelationAndID(userGuest,  event);
		this.feedback = feedback;
	}

	/**
	 * Helper method for setting the embedded Id fields together with the relation fields; 
	 * @param guest
	 * @param event
	 */
	private void setRelationAndID(UserEntity guest, EventEntity event) {
		this.userGuest = guest;
		this.event = event; 
		this.id.userGuestId = userGuest.getId();
		this.id.eventId = event.getId();
	}
	

	/**
	 * Adds a Guest to the Event, two directions.
	 * 
	 * @param guest
	 */
	public boolean subscribe(UserEntity guest, EventEntity event) {
		if (event.getUserEntityOwner().equals(guest)) {
			throw new IllegalArgumentException("Trying to subscribe to the owned event");
		}
		if (guest.getSubscriptions().contains(this) && event.getSubscriptions().contains(this)) {
			throw new IllegalArgumentException("Trying to subsribe where subscription already exists");
		}
		setRelationAndID(guest, event);
		boolean res = true;
		res = guest.addSubscription(this) && res;
		res = event.addSubscription(this) && res;
		return res; 
	}
	
	/**
	 * Adds a Guest to the Event, two directions.
	 * 
	 * @param guest
	 */
	public boolean unsubscribe(UserEntity guest, EventEntity event) {
		if (event.getUserEntityOwner().equals(guest)) {
			throw new IllegalArgumentException("Trying to unsubscribe to the owned event");
		}
		if (!guest.getSubscriptions().contains(this) && !event.getSubscriptions().contains(this)) {
			throw new IllegalArgumentException("Trying to unsubsribe from non-existing subscription");
		}
		if (guest.getSubscriptions().contains(this) != event.getSubscriptions().contains(this)) {
			throw new IllegalStateException("Subscription is not consistent across User and Event");
		}
		this.userGuest = guest;
		this.event = event; 
		boolean res = true;
		res = guest.removeSubsription(this) && res;  // what if this command succeeds and the other does not? inconsistent state; 
		res = event.removeSubsription(this) && res;
		return res; 
	}



}
