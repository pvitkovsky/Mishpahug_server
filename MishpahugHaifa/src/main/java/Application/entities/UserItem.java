package Application.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


@Entity
@Table(name="user")
@EqualsAndHashCode
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname; //TODO: unique nickname;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude
    @ElementCollection
    @CollectionTable
    private List<LogsDataItem> logs;
    private String phoneNumber;
    private String eMail;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne                                                                      // Country of user
    @JsonBackReference
    private CountryItem countryItem;

    @ManyToOne                                                                      // City of user
    @JsonBackReference
    private CityItem cityItem;

    @OneToOne(mappedBy = "users_addresses")                                         // Address of user
    @JsonManagedReference
    private AddressItem addressItem;

    @OneToMany(mappedBy = "user_event_owner", cascade = CascadeType.ALL)            // User owner of events
    @JsonManagedReference
    private List<EventItem> eventItemsOwner = new ArrayList<>();

    @ManyToMany(mappedBy = "users_guests_of_events")                                // User a guest in events
    @JsonManagedReference
    private List<EventItem> eventItemsGuest = new ArrayList<>();

    @OneToMany(mappedBy = "user_pictures", cascade = CascadeType.ALL)              // Pictures of user
    @JsonManagedReference
    private List<PictureItem> pictureItems = new ArrayList<>();

    @OneToMany(mappedBy = "user_feedBacks", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FeedBackItem> feedBackItems = new ArrayList<>();

    public enum UserRole {
    	ADMIN,
    	AUTHORISED,
    	SUSPENDED,
    }


}
