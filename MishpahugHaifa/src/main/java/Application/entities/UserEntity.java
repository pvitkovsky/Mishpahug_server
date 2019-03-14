package Application.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Application.entities.values.PictureValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "nickname" }) })
@ToString(exclude = { "eventItemsOwner", "eventItemsGuest", "pictureItems", "feedBackEntities" })
@EqualsAndHashCode(of = { "nickname" })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "email")
	private String eMail;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@ManyToOne(optional = true)
	@JsonManagedReference //Bidirectional, managed from Address;
	@Setter(AccessLevel.PACKAGE)
	private AddressEntity addressEntity;

	@OneToMany(mappedBy = "userEntityOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference //Bidirectional, managed from here; 
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Set<EventEntity> eventItemsOwner = new HashSet<>();

	@ManyToMany(mappedBy = "userItemsGuests", fetch = FetchType.LAZY) // TODO: immutable getters on sets;
	@JsonManagedReference //Bidirectional, managed from Event;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Set<EventEntity> eventItemsGuest = new HashSet<>();

	@ElementCollection
	@CollectionTable
	@Column(name = "pictures")
	private Set<PictureValue> pictureItems = new HashSet<>();

	@OneToMany(mappedBy = "userItem")
	@MapKey(name = "id")
	@JsonManagedReference
	// TODO: safe bidirectional getter/setter
	private Map<Integer, FeedBackEntity> feedbacks = new HashMap<>();

	public enum UserRole {
		ADMIN, AUTHORISED, SUSPENDED,
	}

	/**
	 * Adds an event to the set of events owned by this user, transferring it from
	 * any previous users;
	 * 
	 * @param event EventEntity that has this user as the owner.
	 * @return true if the event was added; false if the event was not added, as it
	 *         is already in the set.
	 */

	public boolean makeOwner(EventEntity event) {
		if (event.getUserEntityOwner() == null) { // transient state;
			event.setUserEntityOwner(this);
		}
		if (!event.getUserEntityOwner().equals(this)) {
			throw new IllegalArgumentException("Trying to make this user owner of event that belongs to another");
		}
		return eventItemsOwner.add(event); // TODO: thread safety argument;
	}

	/**
	 * Adds an event to the set of events owned by another user, transferring it
	 * from this;
	 * 
	 * @param event    EventEntity that has this user as the owner.
	 * @param newOwner any another user
	 * @return true if the event was added; false if the event was not added, as it
	 *         is already in the set.
	 */
	public boolean transferOwnedEvent(EventEntity event, UserEntity newOwner) {
		if (event.getUserEntityOwner() != null && !event.getUserEntityOwner().equals(this)) {
			throw new IllegalArgumentException("Trying to transfer event with another owner\"");
		}
		eventItemsOwner.remove(event);
		event.setUserEntityOwner(newOwner);
		return newOwner.makeOwner(event);

	}

	/**
	 * Removing owned event, event is deleted once the user is merged;
	 * 
	 * @param event
	 */
	public boolean removeOwnedEvent(EventEntity event) {
		if (!event.getUserEntityOwner().equals(this)) {
			throw new IllegalArgumentException("Trying to remove event with another owner");
		}
		if (!eventItemsOwner.contains(event)) {
			throw new IllegalStateException(
					"Event has user set as owner, but not present in the user's collection of owned events");
		}
		for (UserEntity guest : event.getUserItemsGuestsOfEvents()) {
			event.unSubscribe(guest);
		}
		return eventItemsOwner.remove(event); // TODO: thread safety argument;
	}

	/**
	 * Immutable wrapper over events owned by this user;
	 */
	public Set<EventEntity> getEventEntityOwner() {
		return Collections.unmodifiableSet(eventItemsOwner);
	}

	/**
	 * Protected way to add SubscribedEvent;
	 * 
	 * @param city
	 * @return
	 */
	protected boolean addSubsctibedEvent(EventEntity eventEntity) {
		return eventItemsGuest.add(eventEntity);
	}

	/**
	 * SubscribedEvent is not deleted once the user is merged;
	 * 
	 * @param city
	 * @return
	 */
	protected boolean removeSubsctibedEvent(EventEntity eventEntity) {
		return eventItemsGuest.remove(eventEntity);
	}

	/**
	 * Immutable wrapper over events guested by this user;
	 */
	public Set<EventEntity> getEventEntityGuest() {
		return Collections.unmodifiableSet(eventItemsGuest);
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
