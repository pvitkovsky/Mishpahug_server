package Application.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import Application.entities.values.LogsDataValue;
import Application.entities.values.PictureValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "eventItemsOwner", "eventItemsGuest", "pictureItems", "feedBackEntities" })
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nickname")
	private String nickname; // TODO: unique nickname;

    @Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;


	@ElementCollection
	@CollectionTable
	private List<LogsDataValue> logs;

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

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // User owner of events
	@Column(unique = true)
	@JsonManagedReference
	private Set<EventEntity> eventItemsOwner = new HashSet<>();

	@ManyToMany(mappedBy = "userEntity") // User a guest in events
	@JsonManagedReference
	private Set<EventEntity> eventItemsGuest = new HashSet<>();

	@ElementCollection
	@CollectionTable
    @Column(name = "pictures")
	private Set<PictureValue> pictureItems = new HashSet<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<FeedBackEntity> feedBackEntities = new HashSet<>();

	public enum UserRole {
		ADMIN, AUTHORISED, SUSPENDED,
	}
}
