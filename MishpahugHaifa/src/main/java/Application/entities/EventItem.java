package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "eventlist")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "userItemsGuestsOfEvents" })
@ToString
public class EventItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate date;
	private LocalTime time;
	private String nameOfEvent;
	private EventRatingValue ratings;

	@ManyToOne
	@JsonManagedReference
	private KichenTypeItem kichenTypeItem;

	@Enumerated(EnumType.STRING)
	private EventStatus Status;

	@ManyToOne
	@JoinColumn(nullable = false) //there must be an owner for every item; TODO: cascade operations
	@JsonBackReference
	private UserItem userItemOwner;

	@ManyToOne
	@JsonBackReference
	private AddressItem addressItem;

	@ManyToMany
	@JsonBackReference
	private Set<UserItem> userItemsGuestsOfEvents = new HashSet<>();

	@OneToMany(mappedBy = "eventItem", cascade = CascadeType.ALL) // All feedBacks of event
	@JsonManagedReference
	private Set<FeedBackItem> feedBackItems = new HashSet<>();

	public enum EventStatus {
		CREATED, PENDING, COMPLETE, CANCELED
	}

}
