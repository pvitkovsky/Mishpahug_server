package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
	private List<UserItem> userItemsGuestsOfEvents = new ArrayList<>();

	@OneToMany(mappedBy = "eventItem", cascade = CascadeType.ALL) // All feedBacks of event
	@JsonManagedReference
	private List<FeedBackItem> feedBackItems = new ArrayList<>();

	public enum EventStatus {
		CREATED, PENDING, COMPLETE, CANCELED
	}

}
