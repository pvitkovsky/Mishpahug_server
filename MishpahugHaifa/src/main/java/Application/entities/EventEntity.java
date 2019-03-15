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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "eventlist", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "user_entity_owner", "date", "time", "name_of_event" }) })
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "userEntityOwner", "date", "time", "nameOfEvent" }) // business key;
@ToString(exclude = { "userItemsGuests", "feedbacks" })
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "name_of_event")
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
	@JoinColumn(name = "user_entity_owner")
	@JsonBackReference //Bidirectional, managed from User; 
	@Setter(AccessLevel.PACKAGE)
	private UserEntity userEntityOwner;

	@ManyToOne(optional = true)
	@JsonBackReference //Bidirectional, managed from Address; 
	@Setter(AccessLevel.PACKAGE)
	private AddressEntity addressEntity;

	@ManyToMany(cascade = CascadeType.PERSIST) 
	@JoinTable(name = "USER_EVENT", joinColumns = { @JoinColumn(name = "EVENT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_ID") })
	@JsonBackReference //Bidirectional, managed from here; 
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Set<UserEntity> userItemsGuests = new HashSet<>();

	@OneToMany(mappedBy = "eventItem")
	@MapKey(name = "id")
	@JsonManagedReference
	// TODO: safe bidirectional getter/setter
	private Map<Integer, FeedBackEntity> feedbacks = new HashMap<>();

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
	 * Adds a Guest to the Event, two directions.
	 * 
	 * @param guest
	 */
	public boolean subscribe(UserEntity guest) {
		if (userEntityOwner.equals(guest)) {
			throw new IllegalArgumentException("Trying to subscribe to the owned event");
		}

		guest.addSubsctibedEvent(this);
		return userItemsGuests.add(guest);
	}

	/**
	 * Removes a Guest from the Event, two directions.
	 * 
	 * @param guest
	 */
	public boolean unSubscribe(UserEntity guest) {
		if (userEntityOwner.equals(guest)) {
			throw new IllegalArgumentException("Trying to unsubscribe from the owned event");
		}
		if (!userItemsGuests.contains(guest)) {
			throw new IllegalArgumentException("Not subscribed and trying to unsubscibe");
		}
		if (userItemsGuests.contains(guest) && !guest.getEventEntityGuest().contains(this)) { //
			throw new IllegalStateException(
					"User is guest of event, but his set of subscriptions does not contain this event");
		}
		guest.removeSubsctibedEvent(this);
		return userItemsGuests.remove(guest);
	}

	/**
	 * Immutable wrapper over Guests;
	 * 
	 * @return
	 */
	public Set<UserEntity> getUserItemsGuestsOfEvents() {
		return Collections.unmodifiableSet(userItemsGuests);
	}

	/**
	 * Adding feedback;
	 * 
	 * @param feedback
	 */
	// TODO: immutable getter; defensive coding
	public void addFeedBack(FeedBackEntity feedback) {

		feedbacks.put(feedback.getId(), feedback);

	}
}
