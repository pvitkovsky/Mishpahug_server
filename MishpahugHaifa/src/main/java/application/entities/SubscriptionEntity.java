package application.entities;

import application.entities.values.FeedBackValue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
/*
 * No @AllArgsConstructor because there's an embedded composite ID whose fields
 * must be set manually;
 */
@NoArgsConstructor
//@EqualsAndHashCode(of = {"userGuest", "event"})
@Entity
@Table(name = "user_event_guest", uniqueConstraints = {@UniqueConstraint(columnNames = {"GUEST_ID", "EVENT_ID"})})
@ToString
@Slf4j
/**
 * Represents a subscription. You shouldn't explicitly save this class, it is managed by cascade from User and Event; 
 *
 */
public class SubscriptionEntity {

	@Embeddable
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	@ToString
	@Getter
	public static class EventGuestId implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "GUEST_ID")
		private Integer userGuestId;

		@Column(name = "EVENT_ID")
		private Integer eventId;

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
	@Setter(AccessLevel.NONE)
	private EventGuestId id = new EventGuestId();

	@ManyToOne // TODO: cascading
	@JoinColumn(name = "GUEST_ID", insertable = false, updatable = false) // relation column names should match with
																			// embedded id column names;
	@Setter(AccessLevel.NONE)
	@JsonBackReference("guestOfSubscription")
	private UserEntity guest;

	@ManyToOne // TODO: cascading
	@JoinColumn(name = "EVENT_ID", insertable = false, updatable = false)
	@Setter(AccessLevel.NONE)
	@JsonBackReference("eventOfSubscription")
	private EventEntity event;

	@Embedded
	private FeedBackValue feedback;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Setter(AccessLevel.NONE)
	private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

	public enum SubscriptionStatus implements StatusChanger {
		ACTIVE(e -> e.activate()), CANCELED(e -> e.cancel()), DEACTIVATED(e -> e.deactivate()), PENDINGFORDELETION(
				e -> e.putIntoDeletionQueue());

		private StatusChanger changer;

		private SubscriptionStatus(StatusChanger changer) {
			this.changer = changer;
		}

		@Override
		public void change(SubscriptionEntity subscription) {
			changer.change(subscription);
		}
	}

	@FunctionalInterface
	private interface StatusChanger {
		void change(SubscriptionEntity subscription);
	}
	
	public SubscriptionEntity(UserEntity userGuest, EventEntity event) {
		super();
		setRelationAndID(userGuest, event);
	}

	public SubscriptionEntity(EventGuestId id, UserEntity userGuest, EventEntity event, FeedBackValue feedback) {
		super();
		this.id = id;
		setRelationAndID(userGuest, event);
		this.feedback = feedback;
	}

	/**
	 * Helper method for setting the embedded Id fields together with the relation
	 * fields, and setting bidirectional links
	 * 
	 * @param guest
	 * @param event
	 */
	private void setRelationAndID(UserEntity guest, EventEntity event) {
		checkEventAndID(guest, event);
		this.id.userGuestId = guest.getId();
		this.id.eventId = event.getId();
		this.guest = guest;
		this.event = event;
		boolean wasAdded = true;
		wasAdded = this.guest.addSubscription(this) && wasAdded;
		wasAdded = this.event.addSubscription(this) && wasAdded;
		//TODO: consider using wasAdded to track inconsistency in related sets;  
	}
	
	private void checkEventAndID(UserEntity guest, EventEntity event) {
		if( event.getUserEntityOwner() == null ) { //TODO: events should always be consistent!
			throw new IllegalStateException("Trying to create a subscription for event that is in the inconsistent state");
		}
		if (event.getUserEntityOwner().equals(guest)) {
			throw new IllegalArgumentException("Trying to subscribe to the owned event");
		}
		if (this.guest == null && this.event == null) {
			return;
		} else {
			throw new IllegalArgumentException("Trying to subscribe, but subscription has user and event already");
		}
	}
	
	/**
	 * Changes this subscription's status, validating the parameter
	 * 
	 * @param status
	 *            must be equal to one of UserStatus values;
	 */
	public void changeStatus(String status) {
		SubscriptionStatus newStatus;
		try {
			newStatus = SubscriptionStatus.valueOf(status);
		} catch (Exception e) {
			throw new IllegalArgumentException("Not found SubscriptionStatus with name " + status);
		}
		newStatus.change(this);
	}
	/**
	 * Checks the correct state of all bidirectional relations in this entity
	 */
	public void checkEventIntegrity() {
		if (!guest.getSubscriptions().contains(this)) {
			throw new IllegalStateException(
					"Subscription has guest, but not present in the guest's collection of subscriptions: " + guest);
		}
		if (!event.getSubscriptions().contains(this)) {
			throw new IllegalStateException(
					"Subscription points to event, but not present in the events's collection of subscriptions: " + event);
		}
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
	 * Launched on remove() from this entity's repository. Checks if the guest-event relation is OK to delete and then nullifies it. 
	 */
	@PreRemove
	public void nullifyForRemoval() {
		if (!isPendingForDeletion()) {
			throw new IllegalArgumentException("EventGuestRelation must be first putIntoDeletionQueue");	
		}
		if(this.guest.getSubscriptions().contains(this) || this.event.getSubscriptions().contains(this)) {
			unsubscribe(this.guest, this.event); // if at least one of the many-to-many entities contains the intermediate entity, we fire the full consistency check. 
		}
	}


	/**
	 * @return true if this subscription is active;
	 */
	public boolean isActive() {
		return this.status == SubscriptionStatus.ACTIVE;
	}
	
	/**
	 * @return true if this subscription is active;
	 */
	public boolean isDeactivated() {
		return this.status == SubscriptionStatus.DEACTIVATED;
	}
	
	/**
	 * @return true if this subscription is deactivated;
	 */
	public boolean isCanceled() {
		return this.status == SubscriptionStatus.CANCELED;
	}
	
	/**
	 * @return true if this subscription is ready to be unsubscribed and deleted;
	 */
	public boolean isPendingForDeletion() {
		return this.status == SubscriptionStatus.PENDINGFORDELETION;
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
		if (!isActive()) {
			throw new IllegalArgumentException("Trying to deactivate, but subscription has status" + this.status);
		}	
		this.status = SubscriptionStatus.DEACTIVATED;
	}


	/**
	 * Puts the subscription into the deletion queue;
	 */
	//TODO: can't cancel deletion queue?
	public void putIntoDeletionQueue() {
		this.status = SubscriptionStatus.PENDINGFORDELETION;
	}
}
