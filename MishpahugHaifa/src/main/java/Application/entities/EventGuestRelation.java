package Application.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import Application.entities.values.FeedBackValue;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"userGuest", "event"})
@Entity
@Table(name = "user_event_guest", uniqueConstraints = { @UniqueConstraint(columnNames = { "userGuest", "event"}) })
@ToString
public class EventGuestRelation {

	@Id
	@ManyToOne
	@JoinColumn
	@Setter(AccessLevel.PACKAGE)
	private UserEntity userGuest;

	@Id
	@ManyToOne
	@JoinColumn
	@Setter(AccessLevel.PACKAGE)
	private EventEntity event;
	
	//NEEDS EMBEDDED PK pls; 
	
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
