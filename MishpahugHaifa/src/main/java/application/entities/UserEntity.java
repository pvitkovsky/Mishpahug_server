package application.entities;

import application.entities.properties.*;
import application.entities.values.PictureValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email"}), @UniqueConstraint(columnNames = { "username"}) })
@ToString(exclude = { "eventItemsOwner", "subscriptions", "pictureItems" })
@EqualsAndHashCode(of = { "userName" })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
	@Id
	@Column(name = "User_Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@NotNull
	@lombok.NonNull
	@Column(name = "email", nullable = false)
	private String eMail;

	@NotNull
	@lombok.NonNull
	@Column(name = "username", length = 36, nullable = false)
	@Setter(AccessLevel.NONE)
	private String userName;

	@Column(name = "Encryted_Password", length = 128)
	/*
	 * @JsonInclude(Include.NON_NULL) on class or @JsonInclude(Include.NON_NULL)
	 * here to prevent this from being serialized as null
	 */
	private String encrytedPassword;
	
	@Column(name = "dateofbirth")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfBirth;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	private UserStatus status = UserStatus.ACTIVE;

	public enum UserStatus implements StatusChanger {
		ACTIVE(u -> u.activate()), DEACTIVATED(u -> u.deactivate()), PENDINGFORDELETION(u -> u.putIntoDeletionQueue());

		private StatusChanger changer;

		private UserStatus(StatusChanger changer) {
			this.changer = changer;
		}

		@Override
		public void change(UserEntity user) {
			changer.change(user);
		}
	}

	@FunctionalInterface
	private interface StatusChanger {
		void change(UserEntity user);
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = true) // Unidirectional
	private MaritalStatusEntity maritalStatus;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = true) // Unidirectional
	private GenderEntity gender;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = true) // Unidirectional;
	private KitchenTypeEntity kitchenType;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = true) // Unidirectional;
	private ReligionEntity religion;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = true) // Unidirectional; //TODO:
																						// serializes only address not
																						// city or country; cause -
																						// innecessary bidirections;
	private AddressEntity addressEntity;

	@OneToMany(mappedBy = "userEntityOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference("userEventOwner") // Bidirectional, managed from here;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	@JsonProperty("owned_events")
	//TODO: rename to ownedEvents please; 
	private Set<EventEntity> eventItemsOwner = new HashSet<>();

	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference("guestOfSubscription")
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	@JsonProperty("subscriptions")
	private Set<SubscriptionEntity> subscriptions = new HashSet<>();

	@ElementCollection
	@CollectionTable
	@Column(name = "pictures")
	@Builder.Default
	private Set<PictureValue> pictureItems = new HashSet<>();

	/**
	 * UserEntity: required fields, userName is immutable;
	 */
	public UserEntity(@NotNull String userName, @NotNull String email) {
		this(); //<- or instance variables won't be initialized;  
		if(userName.length() > 36) {
			throw new IllegalArgumentException("userName too long");
		}
		this.userName = userName;
		this.eMail = email;
	}

	public String fieldByName(String fieldName){
		String res = "n/a";
		switch (fieldName){
			case "userName":
				res = this.userName;
				break;
				//TODO
		}
		return res;
	}
	
	/**
	 * Changes this user's status, validating the parameter
	 * 
	 * @param status
	 *            must be equal to one of UserStatus values;
	 */
	public void changeStatus(String status) {
		UserStatus newStatus;
		try {
			newStatus = UserStatus.valueOf(status);
		} catch (Exception e) {
			throw new IllegalArgumentException("Not found UserStatus with name " + status);
		}
		newStatus.change(this);
	}	
	
	/**
	 * Checks the correct state of all bidirectional relations in this entity
	 */
	public void checkForIntegrity() {
		for (EventEntity event : this.getEventEntityOwner()) {
			if(!event.getUserEntityOwner().equals(this)) {
				throw new IllegalStateException("User owns an event that has other user as an owner: " + event);
			}
		}
		for (SubscriptionEntity subscription : this.getSubscriptions()) {
			if(!subscription.getGuest().equals(this)) {
				throw new IllegalStateException("User has a subscription that has other user as a guest: " + subscription);
			}
		}
	}
	
	/**
	 * Checks that the user is OK to delete and then unsubscribes him/her from
	 * everywhere, and his subscribers too;
	 */
	@PreRemove
	private void nullifyForRemoval() {
		if (!isPendingForDeletion()) {
			throw new IllegalArgumentException("User must be first putIntoDeletionQueue");
		}
		unsubscribeEventsAndSubscriptions();
	}

	/**
	 * Immutable wrapper over events owned by this user;
	 */
	@JsonIgnore
	public Set<EventEntity> getEventEntityOwner() {
		return Collections.unmodifiableSet(eventItemsOwner);
	}

	/**
	 * Immutable wrapper over Subscriptions;
	 * 
	 * @return
	 */
	@JsonIgnore
	public Set<SubscriptionEntity> getSubscriptions() {
		return Collections.unmodifiableSet(subscriptions);
	}

	/**
	 * Protected way to add OwnedEvent;
	 * 
	 * @param_city
	 * @return
	 */
	protected boolean addOwnedEvent(EventEntity event) {
		return eventItemsOwner.add(event);
	}

	/**
	 * Protected way to remove OwneddEvent;
	 * 
	 * @param_city
	 * @return
	 */
	protected boolean removeOwnedEvent(EventEntity event) {
		return eventItemsOwner.remove(event);
	}

	/**
	 * Protected way to add SubscribedEvent;
	 * 
	 * @param_city
	 * @return
	 */
	protected boolean addSubscription(SubscriptionEntity subscription) {
		return subscriptions.add(subscription);
	}

	/**
	 * SubscribedEvent is not deleted once the user is merged;
	 * 
	 * @param_city
	 * @return
	 */
	protected boolean removeSubsription(SubscriptionEntity subscription) {
		return subscriptions.remove(subscription);
	}
	
	/**
	 * Unsubscribes user from all subscribed events; unsubscribes others from this
	 * user's owned event; this must be done only before final deletion;
	 */
	/*
	 * Unable to remove while iterated. Had to include collection copy to fix this. 
	 */
	private void unsubscribeEventsAndSubscriptions() {
		
		Set<SubscriptionEntity> removeSubs = new CopyOnWriteArraySet<>(subscriptions);
		Set<EventEntity> removeEvents = new CopyOnWriteArraySet<>(eventItemsOwner);
		removeSubs.forEach(SubscriptionEntity::nullifyForRemoval); 
		removeEvents.forEach(EventEntity::nullifyForRemoval);
		
	}

	/**
	 * Activates all "Deactivated" events and subscription of this user;
	 */
	private void activateEventsAndSubscriptions() {
		subscriptions.forEach(SubscriptionEntity::activate);
		eventItemsOwner.forEach(EventEntity::activate);
	}

	/**
	 * Deactivates all events and subscription of this user;
	 */
	private void deactivateEventsAndSubscriptions() {
		subscriptions.forEach(SubscriptionEntity::deactivate);
		eventItemsOwner.forEach(EventEntity::deactivate);
	}

	/**
	 * Puts all events and subsriptions of this user for deletion;
	 */
	private void putEventsAndSubscriptionsForDeletion() {
		subscriptions.forEach(SubscriptionEntity::putIntoDeletionQueue);
		eventItemsOwner.forEach(EventEntity::putIntoDeletionQueue);
	}

	/**
	 * @return true if this user is activated
	 */
	public boolean isEnabled() {
		return status.equals(UserStatus.ACTIVE);
	}

	/**
	 * @return true if this user is pending for deletion
	 */
	public boolean isPendingForDeletion() {
		return status.equals(UserStatus.PENDINGFORDELETION);
	}

	/**
	 * Activate this user
	 */
	public void activate() {
		activateEventsAndSubscriptions();
		status = UserStatus.ACTIVE;
	}

	/**
	 * Deactivate this user
	 */
	public void deactivate() {
		deactivateEventsAndSubscriptions();
		status = UserStatus.DEACTIVATED;
	}

	/**
	 * Puts this user in the deletion queue
	 */
	public void putIntoDeletionQueue() {
		putEventsAndSubscriptionsForDeletion();
		status = UserStatus.PENDINGFORDELETION;
	}
}
