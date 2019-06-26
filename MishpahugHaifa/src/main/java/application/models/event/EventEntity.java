package application.models.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import application.models.user.UserEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "eventlist", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_owner", "date", "time" }) })
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"userEntityOwner", "date", "time"}) 
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EventEntity {  //TODO: implements ChangeableStatus<EventEntity>{; add choices<EventEntity>; add address
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(name = "date")
	@DateTimeFormat(iso = ISO.DATE)
	@Setter(AccessLevel.NONE) 
	private LocalDate date;

	@NotNull
	@Column(name = "time")
	@Setter(AccessLevel.NONE) 
	private LocalTime time;

	@Column(name = "name_of_event")
	private String nameOfEvent;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_owner")
	@JsonBackReference("userEventOwner") 
	@Setter(AccessLevel.NONE)
	private UserEntity userEntityOwner;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EventStatus status = EventStatus.ACTIVE; //TODO: refactor all these away

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
	 * Constructor for immutability
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
	private void setUserEntityOwner(UserEntity owner) { 
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
	public String toEventUniqueDescription() {
		return this.nameOfEvent + " " + this.date.toString() + " " + this.time.toString();
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
		this.status = EventStatus.ACTIVE;
	}

	/**
	 * Deactivates the event;
	 */
	public void deactivate() {
		this.status = EventStatus.DEACTIVATED;
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
