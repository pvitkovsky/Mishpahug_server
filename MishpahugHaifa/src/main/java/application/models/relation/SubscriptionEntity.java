package application.models.relation;

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

import application.models.event.EventEntity;
import application.models.user.UserEntity;
import application.models.user.values.FeedBackValue;
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
@EqualsAndHashCode(of = {"guest", "event"})
@Entity
@Table(name = "user_event_guest", uniqueConstraints = {@UniqueConstraint(columnNames = {"GUEST_ID", "EVENT_ID"})})
@ToString
/**
 * Represents a subscription. You shouldn't explicitly save this class, it is managed by cascade from User and Event; 
 *
 */
public class SubscriptionEntity {  //TODO: implements ChangeableStatus<SubscriptionEntity>{

	@Embeddable
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode(of= {"userGuestId", "eventId"})
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

	@ManyToOne
	@JoinColumn(name = "GUEST_ID", insertable = false, updatable = false) // relation column names should match with
																			// embedded id column names;
	@Setter(AccessLevel.NONE)
	@JsonBackReference("guestOfSubscription")
	private UserEntity guest;

	@ManyToOne
	@JoinColumn(name = "EVENT_ID", insertable = false, updatable = false)
	@Setter(AccessLevel.NONE)
	@JsonBackReference("eventOfSubscription")
	private EventEntity event;

	@Embedded
	private FeedBackValue feedback;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Setter(AccessLevel.NONE)
	private SubscriptionStatus status = SubscriptionStatus.ACTIVE; //TODO: refactor all these away
 
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
		if (this.guest != null || this.event != null) {
			throw new IllegalArgumentException("Trying to subscribe, but subscription has user and event already");
		} 
		setRelationAndID(userGuest, event);
	}



	/**
	 * Helper method for setting the embedded Id fields together with the relation
	 * fields;
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
	}
	
	private void checkEventAndID(UserEntity guest, EventEntity event) { // bounded context violation
		if( event.getUserEntityOwner() == null ) { 
			throw new IllegalStateException("Trying to create a subscription for event that is in the inconsistent state");
		}
		if (event.getUserEntityOwner().equals(guest)) {
			throw new IllegalArgumentException("Trying to subscribe to the owned event");
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
	 * Launched on remove() from this entity's repository. Checks if the guest-event relation is OK to delete and then nullifies it. 
	 */
	@PreRemove
	public void nullifyForRemoval() {
		if (!isPendingForDeletion()) {
			throw new IllegalArgumentException("EventGuestRelation must be first putIntoDeletionQueue");	
		}
	}
	/**
	 * @return true if this subscription is enabled to be visible to the user ;
	 */
	public boolean isEnabled() {
		return isActive();
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
		if (!isEnabled()) {
			throw new IllegalArgumentException("Trying to cancel, but subscription has status " + this.status);
		}
		this.status = SubscriptionStatus.CANCELED;
	}

	/**
	 * Activates the subscription
	 */
	public void activate() {
		this.status = SubscriptionStatus.ACTIVE;
	}
	
	/**
	 * Deactivates the subscription
	 */
	public void deactivate() {
		this.status = SubscriptionStatus.DEACTIVATED;
	}


	/**
	 * Puts the subscription into the deletion queue;
	 */
	public void putIntoDeletionQueue() {
		this.status = SubscriptionStatus.PENDINGFORDELETION;
	}
}
