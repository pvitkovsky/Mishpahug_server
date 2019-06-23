package application.models.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import application.dto.EventDTO;
import application.models.user.UserEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "eventlist", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_owner", "date", "time" }) })
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(of = {"user_owner", "date", "time"}) // business key;
@ToString(exclude = { "userEntityOwner", "addressEntity", "subscriptions" })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull // can omit nullable=false with Hibernate;
	@Column(name = "date", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	@Setter(AccessLevel.NONE) // TODO: check serialization works;
	private LocalDate date;

	@NotNull
	@Column(name = "time", nullable = false)
	@Setter(AccessLevel.NONE) // TODO: check serialization works;
	// TODO: JSON time format;
	private LocalTime time;

	@Column(name = "name_of_event")
	private String nameOfEvent;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_owner")
	@JsonBackReference("userEventOwner") // Bidirectional, managed from User;
	@Setter(AccessLevel.NONE)
	private UserEntity userEntityOwner;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EventStatus status = EventStatus.ACTIVE;

	public enum EventStatus implements StatusChanger {
		ACTIVE(e -> e.activate()), CANCELED(e -> e.cancel()), DEACTIVATED(e -> e.deactivate()), PENDINGFORDELETION(
				e -> e.putIntoDeletionQueue());

		private StatusChanger changer;

		private EventStatus(StatusChanger changer) {
			this.changer = changer;
		}

		@Override
		public void change(EventEntity event) {
			changer.change(event);
		}
	}

	@FunctionalInterface
	private interface StatusChanger {
		void change(EventEntity event);
	}

	/**
	 * Constructor for immutability TODO: add User into constructor and ensure
	 * cascading
	 * 
	 * @param date
	 * @param time
	 */
	public EventEntity(UserEntity owner, @NotNull LocalDate date, @NotNull LocalTime time) {
		this.date = date;
		this.time = time;
		// Is very dependent on the order of statements here; or hashcode will be messed
		// up
		setUserEntityOwner(owner);
	}

	/**
	 * Setting this event's owner
	 * 
	 * @param owner
	 */
	private void setUserEntityOwner(UserEntity owner) { // TODO: checks;
		if (this.userEntityOwner != null) {
			if (this.userEntityOwner.equals(owner)) {
				return;
			}
		}
		this.userEntityOwner = owner;
	}

	/**
	 * Checks that the event is OK to delete and then unsubscribes all its
	 * subscribers; launched from the owned entity;
	 */
	@PreRemove
	public void nullifyForRemoval() {
		if (!isPendingForDeletion()) {
			throw new IllegalArgumentException("Event must be first putIntoDeletionQueue");
		}
	}

	/**
	 * @return immutable timestamp of event;
	 */
	public LocalDateTime timeDue() {
		return LocalDateTime.of(date, time);
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


	public void convertEventDTO(EventDTO data) {
		//TODO: user into integer
		this.date = data.getDate();
		this.nameOfEvent = data.getNameOfEvent();
		this.time = data.getTime();
	}

	/**
	 * @return true if and only if the event can be visible to the user;
	 */
	public boolean isEnabled() {
		return isDue() || isComplete();
	}

	
	/**
	 * @return true if and only if the event is active and not yet happened;
	 */
	public boolean isDue() {
		return this.status.equals(EventStatus.ACTIVE) && timeDue().isAfter(LocalDateTime.now());
	}

	/**
	 * @return true if and only if the event is active and has happened;
	 */
	public boolean isComplete() {
		return this.status.equals(EventStatus.ACTIVE) && timeDue().isBefore(LocalDateTime.now());
	}

	/**
	 * @return true if the event is canceled;
	 */
	public boolean isCanceled() {
		return this.status.equals(EventStatus.CANCELED);
	}

	/**
	 * @return true if the event is deactivaed (when the owner is deactivated);
	 */
	public boolean isDeactivated() {
		return this.status.equals(EventStatus.DEACTIVATED);
	}

	/**
	 * @return true if the event is pending for deletion;
	 */
	public boolean isPendingForDeletion() {
		return this.status.equals(EventStatus.PENDINGFORDELETION);
	}

	/**
	 * Activates the event;
	 */
	public void activate() {
		if (!isDeactivated()) {
			throw new IllegalArgumentException("trying to activate event, but its status is " + this.status);
		}
		this.status = EventStatus.ACTIVE;
	}

	/**
	 * Deactivates the event;
	 */
	public void deactivate() {
		if (isDue() || isComplete()) {
			this.status = EventStatus.DEACTIVATED;
		} else {
			throw new IllegalArgumentException("trying to deactivate event, but its status is " + this.status);
		}
	}

	/**
	 * Cancels the event;
	 */
	public void cancel() {
		if (!isDue()) {
			throw new IllegalArgumentException("trying to cancel event, but its status is " + this.status);
		}
		this.status = EventStatus.CANCELED;
	}

	/**
	 * Puts the event into delete queue;
	 */
	public void putIntoDeletionQueue() {
		this.status = EventStatus.PENDINGFORDELETION;
	}

	/**
	 * Changes this event's status, validating the parameter
	 * 
	 * @param status
	 *            must be equal to one of UserStatus values;
	 */
	public void changeStatus(String status) {
		EventStatus newStatus;
		try {
			newStatus = EventStatus.valueOf(status);
		} catch (Exception e) {
			throw new IllegalArgumentException("Not found EventStatus with name " + status);
		}
		newStatus.change(this);
	}

	public String fieldByName(String fieldName){
		String res = "n/a";
		switch (fieldName){
			case "name":
				res = this.nameOfEvent;
				break;
				//TODO
		}
		return res;
	}

}
