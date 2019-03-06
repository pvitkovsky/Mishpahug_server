package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "eventlist", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"user_entity_owner", "date", "time", "name_of_event"})
	})	
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"userEntityOwner", "date", "time", "nameOfEvent"}) // business key; 
@ToString(exclude = { "userItemsGuestsOfEvents", "feedBackEntities" })
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="date")
	private LocalDate date;

	@Column(name="time")
	private LocalTime time;

	@Column(name="name_of_event")
	private String nameOfEvent;

    @JsonManagedReference
	private HashMap<Integer, FeedBackEntity> feedbacks;

	@ManyToOne
	@JsonManagedReference
	private KichenTypeEntity kichenTypeEntity;


	@ManyToOne
	@JsonManagedReference
	private HoliDayEntity holiDayEntity;


	public HoliDayEntity getHoliDayEntity() {
		return 	holiDayEntity;
	}

	public void setHoliDayEntity(HoliDayEntity holiDayEntity) {
		this.holiDayEntity = holiDayEntity;
	}

	@Enumerated(EnumType.STRING)
	private EventStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_entity_owner")
	@JsonBackReference
	private UserEntity userEntityOwner;

	@ManyToOne
	@JsonBackReference
	private AddressEntity addressEntity;

	
	@ManyToMany(cascade = CascadeType.PERSIST) // User a guest in events
	@JoinTable(name = "USER_EVENT",
    joinColumns = {
        @JoinColumn(name = "EVENT_ID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "USER_ID")
    })
	@JsonBackReference
	private Set<UserEntity> userItemsGuestsOfEvents = new HashSet<>();

	@OneToMany(mappedBy = "eventItem", cascade = CascadeType.ALL) // All feedBacks of event
	@JsonManagedReference
	private List<FeedBackEntity> feedBackEntities = new ArrayList<>();

	public enum EventStatus {
		CREATED, PENDING, COMPLETE, CANCELED
	}

	/*
	 * TODO: consider embedded business key with its own methods; 
	 */
	/**
	 * Returns part of business key as string for logging;
	 * @return part of business key that allows to uniquely identify event among a list of user's events; 
	 */
	public String toEventUniqueDescription() {
		return this.nameOfEvent + " " + this.date.toString() + " " + this.time.toString();
	}

	public void subscribe(UserEntity guest) {
		guest.subscribeTo(this);
	}	
	
	public void unSubscribe(UserEntity guest) {
		guest.unsubscribeFrom(this);
	}	
}
