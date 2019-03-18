package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import Application.entities.values.FeedBackValue;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "eventlist", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "user_owner", "date", "time", "name_of_event" }) })
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "userEntityOwner", "date", "time", "nameOfEvent" }) // business key;
@ToString(exclude = { "userEntityOwner", "addressEntity" , "subscriptions" })
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@NotNull TODO: clarify
	@Column(name = "date", nullable = false)
	private LocalDate date;

	//@NotNull TODO: clarify
	@Column(name = "time", nullable = false)
	private LocalTime time;

	//@NotNull TODO: clarify
	@Column(name = "name_of_event", nullable = false)
	private String nameOfEvent;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JsonManagedReference //Unidirectional;
	private KichenTypeEntity kichenTypeEntity;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true) 
	@JsonManagedReference //Unidirectional;
	private HoliDayEntity holiDayEntity;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EventStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_owner")
	@JsonBackReference //Bidirectional, managed from User; 
	@Setter(AccessLevel.PACKAGE)
	private UserEntity userEntityOwner;

	@ManyToOne(optional = true)
	@JsonBackReference //Bidirectional, managed from Address; 
	@Setter(AccessLevel.PACKAGE)
	private AddressEntity addressEntity;

	@OneToMany(mappedBy = "id.eventId" , cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) 
	@JsonBackReference //TODO: safe bidir getters/setters; feedback
	private Set<EventGuestRelation> subscriptions = new HashSet<>();

	public enum EventStatus {
		CREATED, PENDING, COMPLETE, CANCELED
	}

	/**
	 * Returns part of business key as string for logging;
	 * 
	 * @return part of business key that allows to uniquely identify event among a
	 *         list of user's events;
	 */
	// TODO: consider embedded business key with its own methods;
	public String toEventUniqueDescription() {
		return this.nameOfEvent + " " + this.date.toString() + " " + this.time.toString();
	}
	
	/**
	 * Protected way to add SubscribedEvent;
	 * 
	 * @param_city
	 * @return
	 */
	protected boolean addSubscription(EventGuestRelation subscription) {
		return subscriptions.add(subscription);
	}

	/**
	 * SubscribedEvent is not deleted once the user is merged;
	 * 
	 * @param_city
	 * @return
	 */
	protected boolean removeSubsription(EventGuestRelation subscription) {
		return subscriptions.remove(subscription);
	}
	
	/**
	 * Immutable wrapper over Subscriptions;
	 * 
	 * @return
	 */
	public Set<EventGuestRelation> getUserItemsGuestsOfEvents() {
		return Collections.unmodifiableSet(subscriptions);
	}
//
//	/**
//	 * Adds a Guest to the Event, two directions.
//	 * 
//	 * @param guest
//	 */
//	public boolean subscribe(UserEntity guest) {
//		if (userEntityOwner.equals(guest)) {
//			throw new IllegalArgumentException("Trying to subscribe to the owned event");
//		}
//		EventGuestRelation subscribed = new EventGuestRelation(guest, this); 
//		guest.addSubscription(subscribed);
//		return subscriptions.add(subscribed);
//	}
//
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
//

//
//	/**
//	 * Adding feedback;
//	 * 
//	 * @param feedback
//	 */
//	public void addFeedBack(UserEntity guest, FeedBackValue feedback) {
//		EventGuestRelation subscribed = validateSubscription(guest, "put feedback");
//		subscribed.setFeedback(feedback);
//	}
//
//	/**
//	 * Checking that guest is part of this event; 
//	 * @param guest
//	 * @param subscribed
//	 */
//	private EventGuestRelation validateSubscription(UserEntity guest, String action) {
//		EventGuestRelation subscribed = new EventGuestRelation(guest, this); 
//		if (!subscriptions.contains(subscribed)) {
//			throw new IllegalArgumentException("Not subscribed and trying to "+action);
//		}
//		if (subscriptions.contains(subscribed) && !guest.getSubscriptions().contains(subscribed)) { //
//			throw new IllegalStateException(
//					"User is guest of event, but his set of subscriptions does not contain this event");
//		}
//	}
}
