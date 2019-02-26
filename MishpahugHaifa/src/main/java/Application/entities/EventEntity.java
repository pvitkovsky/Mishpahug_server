package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "eventlist", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"date", "time", "nameOfEvent"})
	})	
//@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(of = {"date", "time", "nameOfEvent"}) // business key; 
@ToString(exclude = { "userItemsGuestsOfEvents", "feedBackEntities" })
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate date;

	private LocalTime time;

	private String nameOfEvent;

    @JsonManagedReference
	private HashMap<Integer, FeedBackEntity> feedbacks;

	@ManyToOne
	@JsonManagedReference
	private KichenTypeEntity kichenTypeEntity;


	@ManyToOne
	@JsonManagedReference
	private HoliDayEntity holiDayEntity;

	public EventEntity(LocalDate date,
					   LocalTime time,
					   String nameOfEvent,
					   HashMap<Integer,
							   FeedBackEntity> feedbacks,
					   KichenTypeEntity kichenTypeEntity,
					   HoliDayEntity holiDayEntity,
					   EventStatus status,
					   UserEntity userEntityOwner,
					   AddressEntity addressEntity,
					   List<UserEntity> userItemsGuestsOfEvents,
					   List<FeedBackEntity> feedBackEntities) {
		this.date = date;
		this.time = time;
		this.nameOfEvent = nameOfEvent;
		this.feedbacks = feedbacks;
		this.kichenTypeEntity = kichenTypeEntity;
		this.holiDayEntity = holiDayEntity;
		this.status = status;
		this.userEntityOwner = userEntityOwner;
		this.addressEntity = addressEntity;
		this.userItemsGuestsOfEvents = userItemsGuestsOfEvents;
		this.feedBackEntities = feedBackEntities;
	}

	public HoliDayEntity getHoliDayEntity() {
		return holiDayEntity;
	}

	public void setHoliDayEntity(HoliDayEntity holiDayEntity) {
		this.holiDayEntity = holiDayEntity;
	}

	@Enumerated(EnumType.STRING)
	private EventStatus status;

	@ManyToOne
	@JsonBackReference
	private UserEntity userEntityOwner;

	@ManyToOne
	@JsonBackReference
	private AddressEntity addressEntity;

	@ManyToMany
	@JsonBackReference
	private List<UserEntity> userItemsGuestsOfEvents = new ArrayList<>();

	@OneToMany(mappedBy = "eventItem", cascade = CascadeType.ALL) // All feedBacks of event
	@JsonManagedReference
	private List<FeedBackEntity> feedBackEntities = new ArrayList<>();

	public enum EventStatus {
		CREATED, PENDING, COMPLETE, CANCELED
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getNameOfEvent() {
		return nameOfEvent;
	}

	public void setNameOfEvent(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
	}

	public HashMap<Integer, FeedBackEntity> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(HashMap<Integer, FeedBackEntity> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public KichenTypeEntity getKichenTypeEntity() {
		return kichenTypeEntity;
	}

	public void setKichenTypeEntity(KichenTypeEntity kichenTypeEntity) {
		this.kichenTypeEntity = kichenTypeEntity;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

	public UserEntity getUserEntityOwner() {
		return userEntityOwner;
	}

	public void setUserEntityOwner(UserEntity userEntityOwner) {
		this.userEntityOwner = userEntityOwner;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

	public List<UserEntity> getUserItemsGuestsOfEvents() {
		return userItemsGuestsOfEvents;
	}

	public void setUserItemsGuestsOfEvents(List<UserEntity> userItemsGuestsOfEvents) {
		this.userItemsGuestsOfEvents = userItemsGuestsOfEvents;
	}

	public List<FeedBackEntity> getFeedBackEntities() {
		return feedBackEntities;
	}

	public void setFeedBackEntities(List<FeedBackEntity> feedBackEntities) {
		this.feedBackEntities = feedBackEntities;
	}

	public EventEntity() {
	}

	public EventEntity(LocalDate date, LocalTime time, String nameOfEvent, HashMap<Integer, FeedBackEntity> feedbacks, KichenTypeEntity kichenTypeEntity, EventStatus status, UserEntity userEntityOwner, AddressEntity addressEntity, List<UserEntity> userItemsGuestsOfEvents, List<FeedBackEntity> feedBackEntities) {
		this.date = date;
		this.time = time;
		this.nameOfEvent = nameOfEvent;
		this.feedbacks = feedbacks;
		this.kichenTypeEntity = kichenTypeEntity;
		this.status = status;
		this.userEntityOwner = userEntityOwner;
		this.addressEntity = addressEntity;
		this.userItemsGuestsOfEvents = userItemsGuestsOfEvents;
		this.feedBackEntities = feedBackEntities;
	}	
}
