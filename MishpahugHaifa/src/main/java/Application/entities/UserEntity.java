package application.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

import application.controllers.EncrytedPasswordUtils;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import application.entities.values.PictureValue;
import lombok.*;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "User_Name" }) })
@ToString(exclude = { "eventItemsOwner", "eventItemsGuest", "pictureItems", "feedbacks" })
@EqualsAndHashCode(of = { "lastName", "firstName", "eMail" })
@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Builder

public class UserEntity {
	@Id
	@Column(name = "User_Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_Id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "phonenumber")
	private String phoneNumber;


	@Column(name = "email")
	private String eMail;


	@Column(name = "User_Name", length = 36)
	private String userName;

	@Column(name = "Encryted_Password", length = 128)
	@Setter(AccessLevel.NONE)
	private String encrytedPassword;

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = EncrytedPasswordUtils.encrytePassword(encrytedPassword);
	}

	@Column(name = "Enabled", length = 1)
	private boolean enabled;

	@ManyToOne(optional = true)
	@JsonManagedReference //Bidirectional, managed from Address;
	@Setter(AccessLevel.PACKAGE)
	private AddressEntity addressEntity;

	@OneToMany(mappedBy = "userEntityOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference //Bidirectional, managed from here; 
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	private Set<EventEntity> eventItemsOwner = new HashSet<>();

	@ManyToMany(mappedBy = "userItemsGuests", fetch = FetchType.LAZY) // TODO: immutable getters on sets;
	@JsonManagedReference //Bidirectional, managed from Event;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	private Set<EventEntity> eventItemsGuest = new HashSet<>();

	@ElementCollection
	@CollectionTable
	@Column(name = "pictures")
	@Builder.Default
	private Set<PictureValue> pictureItems = new HashSet<>();

	@OneToMany(mappedBy = "userItem")
	@MapKey(name = "id")
	@JsonManagedReference
	@Builder.Default
	// TODO: safe bidirectional getter/setter
	private Map<Integer, FeedBackEntity> feedbacks = new HashMap<>();


	/**
	 * Adds an event to the set of events owned by this user, transferring it from
	 * any previous users;
	 * 
	 * @param event EventEntity that has this user as the owner.
	 * @return true if the event was added; false if the event was not added, as it
	 *         is already in the set.
	 */

	public boolean makeOwner(EventEntity event) {
			event.setUserEntityOwner(this);
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
	 * @param_city
	 * @return
	 */
	protected boolean addSubsctibedEvent(EventEntity eventEntity) {
		return eventItemsGuest.add(eventEntity);
	}

	/**
	 * SubscribedEvent is not deleted once the user is merged;
	 * 
	 * @param_city
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
