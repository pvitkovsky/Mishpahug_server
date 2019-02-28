package Application.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Application.entities.values.PictureValue;
import lombok.*;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "nickname" }) })
@ToString(exclude = { "eventItemsOwner", "eventItemsGuest", "pictureItems", "feedBackEntities" })
@AllArgsConstructor
@Getter
@Setter
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

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRole role;

	@JsonManagedReference
	@Column(name = "feedbacks")
	private HashMap<Integer, FeedBackEntity> feedBacks;

	@OneToOne(mappedBy = "userEntity") // Address of user
	@JsonManagedReference
	private AddressEntity addressEntity;

	@OneToMany(mappedBy = "userEntityOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // User owner of events
	@JsonManagedReference
	private Set<EventEntity> eventItemsOwner = new HashSet<>();

	@ManyToMany(mappedBy = "userItemsGuestsOfEvents") // User a guest in events
	@JsonManagedReference
	private Set<EventEntity> eventItemsGuest = new HashSet<>();

	@ElementCollection
	@CollectionTable
	@Column(name = "pictures")
	private Set<PictureValue> pictureItems = new HashSet<>();

	@OneToMany(mappedBy = "userItem", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<FeedBackEntity> feedBackEntities = new HashSet<>();

	public enum UserRole {
		ADMIN, AUTHORISED, SUSPENDED,
	}


	/**
	 * Adds an event to the set of events owned by this user. Event must have this
	 * user as the owner.
	 * 
	 * @param event
	 *            EventEntity that has this user as the owner.
	 * @return true if the event was added; false if the event was not added, as it
	 *         is already in the set.
	 */
	public boolean addEvent(EventEntity event) {
		if (!event.getUserEntityOwner().equals(this)) {
			throw new IllegalArgumentException("Trying to add event with another owner");
		}
		return eventItemsOwner.add(event); // TODO: thread safety argument;

	}

	/**
	 * Removes an event from the set of events owned by this user, and sets another
	 * UserEntity as its owner.
	 * 
	 * @param event
	 *            EventEntity that has this user as the owner.
	 * @param newOwner
	 *            new owning user;
	 */
	public void transferEvent(EventEntity event, UserEntity newOwner) {
		if (!event.getUserEntityOwner().equals(this)) {
			/*
			 * Fail-fast: you shouldn't try to transfer Event E from user A if user A does not have event E!
			 */
			throw new IllegalArgumentException("Trying to remove event with another owner");
		}
		
		if (!this.eventItemsOwner.contains(event)) {
			/*
			 * as Event.setUserEntity is bidirectional, we should never get here; 
			 * TODO: consider deleting this validation. Too paranoid?
			 */	
			throw new IllegalStateException("Event not found in this user's set");
		}
		eventItemsOwner.remove(event);
		event.setUserEntityOwner(newOwner);
	}

	/**
	 * Immutable wrapper over events owned by this user;
	 */
	public Set<EventEntity> getEventEntityOwner() {
		return Collections.unmodifiableSet(eventItemsOwner);
	}

}
