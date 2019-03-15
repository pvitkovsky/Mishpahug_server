package Application.entities;

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

import Application.entities.values.FeedBackValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode //(of = {"userGuest", "event"})
@Entity
@Table(name = "user_event_guest") //, uniqueConstraints = { @UniqueConstraint(columnNames = { "userGuest", "event"}) })
@ToString
public class EventGuestRelation {

	@Embeddable
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	public static class Id implements Serializable {
		
		private static final long serialVersionUID = 1L;

		@Column(name = "GUEST_ID")
		protected Long categoryId;
		
		@Column(name = "EVENT_ID")
		protected Long itemId;	
	}
	
	@EmbeddedId
	protected Id id = new Id();
	

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	@Setter(AccessLevel.PACKAGE)
	private UserEntity userGuest;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	@Setter(AccessLevel.PACKAGE)
	private EventEntity event;

	
	@Embedded
	private FeedBackValue feedback;

	public EventGuestRelation(UserEntity userGuest, EventEntity event) {
		super();
		this.userGuest = userGuest;
		this.event = event;
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
		this.userGuest = guest;
		this.event = event; 
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
		this.userGuest = guest;
		this.event = event; 
		boolean res = true;
//		res = guest.addSubscription(this) && res; TODO: unsub;
//		res = event.addSubscription(this) && res;
		return res; 
	}



//	/**
//	 * Removes a Guest from the Event, two directions.
//	 * 
//	 * @param guest
//	 */
//	public boolean unSubscribe(UserEntity guest) {
//		if (userEntityOwner.equals(guest)) {
//			throw new IllegalArgumentException("Trying to unsubscribe from the owned event");
//		}
//
//		EventGuestRelation subscribed= validateSubscription(guest, "subscribe");
//		guest.removeSubscribedEvent(subscribed);
//		return subscriptions.remove(subscribed);
//	}
}
