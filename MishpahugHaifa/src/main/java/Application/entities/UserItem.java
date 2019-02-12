package Application.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nickname; // TODO: unique nickname;
	private String firstName;
	private String lastName;
	@EqualsAndHashCode.Exclude
	@ElementCollection
	@CollectionTable
	private List<LogsDataValue> logs;
	private String phoneNumber;
	private String eMail;
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@ManyToOne // Country of user
	@JsonBackReference
	private CountryItem countryItem;

	@ManyToOne // City of user
	@JsonBackReference
	private CityItem cityItem;

	@OneToOne(mappedBy = "userItem") // Address of user 
	@JsonManagedReference
	private AddressItem addressItem;
	
	@OneToMany(cascade = CascadeType.PERSIST)  // User owner of events 
	@JoinColumn(unique = true) //One event can't have many owners; TODO: cascade operations
	@JsonManagedReference
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<EventItem> eventItemsOwner = new HashSet<>();

	@ManyToMany(mappedBy = "userItemsGuestsOfEvents") // User a guest in events
	@JsonManagedReference
	private Set<EventItem> eventItemsGuest = new HashSet<>();

	@OneToMany(mappedBy = "userItemOwner", cascade = CascadeType.ALL) // Pictures of user
	@JsonManagedReference
	private Set<PictureItem> pictureItems = new HashSet<>();

	@OneToMany(mappedBy = "userItem", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<FeedBackItem> feedBackItems = new HashSet<>();

	public enum UserRole {
		ADMIN, AUTHORISED, SUSPENDED,
	}
}
