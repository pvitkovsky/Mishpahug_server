package application.entities;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import application.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import application.utils.EncrytedPasswordUtils;
import application.entities.values.PictureValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@ToString(exclude = { "eventItemsOwner", "subscriptions", "pictureItems"})
@EqualsAndHashCode(of = { "eMail" })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder 
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

	@Column(name = "email", nullable = false)
	private String eMail;

	@Column(name = "User_Name", length = 36)
	private String userName;

	@Column(name = "Encryted_Password", length = 128)
	@Setter(AccessLevel.NONE)
	private String encrytedPassword;

	@Column(name = "dateofbirth")
	private LocalDate dateOfBirth;

	public UserEntity(UserDTO data){
		this.setFirstName(data.getFirstName());
		this.setEMail(data.getEMail());
		this.setLastName(data.getLastName());
		this.setPhoneNumber(data.getPhoneNumber());
		this.setUserName(data.getUserName());
		this.setDateOfBirth(data.getDayOfBirth());
		this.setEncrytedPassword(data.getEncrytedPassword());

	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = EncrytedPasswordUtils.encrytePassword(encrytedPassword);
	}

	@Column(name = "Enabled", length = 1)
	private boolean enabled;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JsonManagedReference //Unidirectional;
    @JsonIgnore
	private MarriageStatusEntity marriageStatusEntity;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JsonManagedReference //Unidirectional;
    @JsonIgnore
	private GenderEntity genderEntity;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JsonManagedReference //Unidirectional;
    @JsonIgnore
	private KichenTypeEntity kichenTypeEntity;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JsonManagedReference //Unidirectional;
    @JsonIgnore
	private ReligionEntity religionEntity;

	@ManyToOne(optional = true)
	@JsonManagedReference //Bidirectional, managed from Address;
	@Setter(AccessLevel.PACKAGE)
    @JsonIgnore
	private AddressEntity addressEntity;

	@OneToMany(mappedBy = "userEntityOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference //Bidirectional, managed from here; 
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	private Set<EventEntity> eventItemsOwner = new HashSet<>();

	@OneToMany(mappedBy = "userGuest", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) 
	@JsonManagedReference //TODO: safe bidir getters/setters; feedback
	@Builder.Default
	private Set<EventGuestRelation> subscriptions = new HashSet<>();

	@ElementCollection
	@CollectionTable
	@Column(name = "pictures")
	@Builder.Default
	private Set<PictureValue> pictureItems = new HashSet<>();


	/**
	 * Adds an event to the set of events owned by this user, transferring it from
	 * any previous users;
	 * 
	 * @param event EventEntity that has this user as the owner.
	 * @return true if the event was added; false if the event was not added, as it
	 *         is already in the set.
	 */

	public boolean makeOwner(EventEntity event) {
		//TODO: check that event has its business key not null; or NPE is possible;
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
//		for (EventGuestRelation subscription : event.getUserItemsGuestsOfEvents()) { //TODO: delete subscriptions;
//		}
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
		return subscriptions.remove(subscription); // TODO: is it cascaded?
	}
	
	
	/**
	 * Immutable wrapper over Subscriptions;
	 * 
	 * @return
	 */
	public Set<EventGuestRelation> getUserItemsGuestsOfEvents() {
		return Collections.unmodifiableSet(subscriptions);
	}
}
