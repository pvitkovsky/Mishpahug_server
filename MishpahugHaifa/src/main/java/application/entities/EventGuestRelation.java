package application.entities;

import application.entities.EventEntity.EventStatus;
import application.entities.values.FeedBackValue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
/*
 * No @AllArgsConstructor because there's an embedded composite ID whose fields
 * must be set manually;
 */
@NoArgsConstructor
@EqualsAndHashCode(of = { "userGuest", "event" })
@Entity
@Table(name = "user_event_guest", uniqueConstraints = { @UniqueConstraint(columnNames = { "GUEST_ID", "EVENT_ID" }) })
@ToString
// TODO: how should we handle unsubscriptions (for example, a user deleted
// himseld) after leaving feedback? Feedback will be destroyed.
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

		/**
		 * Extra constructor for type safety;
		 */
		public EventGuestId(UserEntity userGuest, EventEntity event) {
			super();
			this.userGuestId = userGuest.getId();
			this.eventId = event.getId();
		}
	}

	@EmbeddedId
	protected EventGuestId id = new EventGuestId();

	@ManyToOne // TODO: cascading
	@JoinColumn(name = "GUEST_ID", insertable = false, updatable = false) // relation column names should match with
																			// embedded id column names;
	@Setter(AccessLevel.PACKAGE)
	@JsonBackReference("guestOfSubscription")
	private UserEntity userGuest;

	@ManyToOne // TODO: cascading
	@JoinColumn(name = "EVENT_ID", insertable = false, updatable = false)
	@Setter(AccessLevel.PACKAGE)
	@JsonBackReference("eventOfSubscription")
	private EventEntity event;

	@Embedded
	private FeedBackValue feedback;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Setter(AccessLevel.NONE)
	private SubscriptionStatus status = SubscriptionStatus.BLANK;

	public enum SubscriptionStatus {
		BLANK, ACTIVE, CANCELED, DEACTIVATED, DUETODELETION
	}
	
	public EventGuestRelation(UserEntity userGuest, EventEntity event) {
		super();
		setRelationAndID(userGuest, event);
	}

	public EventGuestRelation(EventGuestId id, UserEntity userGuest, EventEntity event, FeedBackValue feedback) {
		super();
		this.id = id;
		setRelationAndID(userGuest, event);
		this.feedback = feedback;
	}

	/**
	 * Helper method for setting the embedded Id fields together with the relation
	 * fields;
	 * 
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
		if (!isReady()) {
			throw new IllegalArgumentException("Trying to subscribe, but subscription has status " + this.status);
		}
		if (event.getUserEntityOwner().equals(guest)) {
			throw new IllegalArgumentException("Trying to subscribe to the owned event");
		}
		if (guest.getSubscriptions().contains(this) && event.getSubscriptions().contains(this)) {
			throw new IllegalArgumentException("Trying to subsribe where subscription already exists");
		}
		setRelationAndID(guest, event);
		this.status = SubscriptionStatus.ACTIVE;
		boolean res = true;
		res = guest.addSubscription(this) && res;
		res = event.addSubscription(this) && res;
		return res;
	}

	/**
	 * Removes a Guest from the Event, two directions.
	 * 
	 * @param guest
	 */
	public boolean unsubscribe(UserEntity guest, EventEntity event) {
		if (!isSubscribed()) {
			throw new IllegalArgumentException("Trying to unsubscribe, but subscription has status " + this.status);
		}
		if (event.getUserEntityOwner().equals(guest)) {
			throw new IllegalArgumentException("Trying to unsubscribe to the owned event");
		}
		if (!guest.getSubscriptions().contains(this) && !event.getSubscriptions().contains(this)) {
			throw new IllegalArgumentException("Trying to unsubsribe from non-existing subscription");
		}
		if (guest.getSubscriptions().contains(this) != event.getSubscriptions().contains(this)) {
			throw new IllegalStateException("Subscription is not consistent across User and Event");
		}
		this.status = SubscriptionStatus.DUETODELETION;
		boolean res = true;
		res = guest.removeSubsription(this) && res; // what if this command succeeds and the other does not?
													// inconsistent state;
		res = event.removeSubsription(this) && res;
		return res;
	}

	/**
	 * Automatically removes a Guest from the Event, two directions.
	 * 
	 * @param guest
	 */
	public boolean unsubscribe() {
		return unsubscribe(this.userGuest, this.event);
	}

	/**
	 * Cancels the subscription
	 */
	public void cancel() {
		if (!isSubscribed()) {
			throw new IllegalArgumentException("Trying to cancel, but subscription has status " + this.status);
		}
		this.status = SubscriptionStatus.CANCELED;
	}

	/**
	 * Deactivates the subscription
	 */
	public void deactivate() {
		if (!isSubscribed()) {
			throw new IllegalArgumentException("Trying to deactivate, but subscription has status " + this.status);
		}
		this.status = SubscriptionStatus.DEACTIVATED;
	}

	/**
	 * @return true if this subscription is ready to be created;
	 */
	public boolean isReady() {
		return this.status == SubscriptionStatus.BLANK;
	}

	/**
	 * @return true if this subscription is created;
	 */
	public boolean isSubscribed() {
		return (this.status == SubscriptionStatus.ACTIVE || this.status == SubscriptionStatus.CANCELED
				|| this.status == SubscriptionStatus.DEACTIVATED);
	}

}
