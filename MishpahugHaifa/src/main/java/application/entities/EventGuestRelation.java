package application.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
		BLANK, ACTIVE, CANCELED, DEACTIVATED, PENDINGFORDELETION
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
	 * @return true if this subscription is ready to be created;
	 */
	public boolean isBlank() {
		return this.status == SubscriptionStatus.BLANK;
	}
	
	/**
	 * @return true if this subscription is active;
	 */
	public boolean isActive() {
		return this.status == SubscriptionStatus.ACTIVE;
	}
	
	/**
	 * @return true if this subscription is deactivated;
	 */
	public boolean isDeactivated() {
		return this.status == SubscriptionStatus.DEACTIVATED;
	}
	
	/**
	 * @return true if this subscription is ready to be unsubscribed and deleted;
	 */
	public boolean isPendingForDeletion() {
		return this.status == SubscriptionStatus.PENDINGFORDELETION;
	}
	
	
	/**
	 * Adds a Guest to the Event, two directions.
	 * 
	 * @param guest
	 */
	public boolean subscribe(UserEntity guest, EventEntity event) {
		if (!isBlank()) {
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
	private boolean unsubscribe(UserEntity guest, EventEntity event) {
		if (event.getUserEntityOwner().equals(guest)) {
			throw new IllegalArgumentException("Trying to unsubscribe to the owned event");
		}
		if (!guest.getSubscriptions().contains(this) && !event.getSubscriptions().contains(this)) {
			throw new IllegalArgumentException("Trying to unsubsribe from non-existing subscription");
		}
		if (guest.getSubscriptions().contains(this) != event.getSubscriptions().contains(this)) {
			throw new IllegalStateException("Subscription is not consistent across User and Event");
		}	
		boolean res = true;
		res = guest.removeSubsription(this) && res; // what if this command succeeds and the other does not?
													// inconsistent state;
		res = event.removeSubsription(this) && res;
		return res;
	}

	/**
	 * Checks that the guest-event relation is OK to delete and then nullifies it; This is launched when User or Event are deleted.
	 */
	public void nullifyForRemoval() {
		if (!isPendingForDeletion()) {
			throw new IllegalArgumentException("EventGuestRelation must be first putIntoDeletionQueue");	
		}
		unsubscribe(this.userGuest, this.event);
	}
	
	/**
	 * Launched on remove() from this entity's repository. Checks if the guest-event relation is OK to delete and then nullifies it. 
	 */
	@PreRemove
	public void nullifyForIndependentRemoval() {
		if (!isPendingForDeletion()) {
			throw new IllegalArgumentException("EventGuestRelation must be first putIntoDeletionQueue");	
		}
		if(this.userGuest.getSubscriptions().contains(this) || this.event.getSubscriptions().contains(this)) {
			unsubscribe(this.userGuest, this.event); // if at least one of the many-to-many entities contains the intermediate entity, we fire the full consistency check. 
		}
	}

	/**
	 * Cancels the subscription, can only be applied to active subscription
	 */
	//TODO: can't re-cancel?
	public void cancel() {
		if (!isActive()) {
			throw new IllegalArgumentException("Trying to cancel, but subscription has status " + this.status);
		}
		this.status = SubscriptionStatus.CANCELED;
	}

	/**
	 * Activates the subscription
	 */
	public void activate() {
		if (!isDeactivated()) {
			throw new IllegalArgumentException("Trying to activate, but subscription has status " + this.status);
		}
		this.status = SubscriptionStatus.ACTIVE;
	}
	
	/**
	 * Deactivates the subscription
	 */
	public void deactivate() {
		if (isBlank()) {
			throw new IllegalArgumentException("Trying to deactivate, but subscription is blank");
		}
		this.status = SubscriptionStatus.DEACTIVATED;
	}


	/**
	 * Deactivates the subscription
	 */
	//TODO: can't cancel deletion queue?
	public void putIntoDeletionQueue() {
		this.status = SubscriptionStatus.PENDINGFORDELETION;
	}



}
