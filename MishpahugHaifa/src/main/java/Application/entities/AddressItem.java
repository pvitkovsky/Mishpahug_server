package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="address")
@Getter @Setter
@ToString
@AllArgsConstructor //do construction without userItem 
@NoArgsConstructor
@EqualsAndHashCode(of = "userItem")
public class AddressItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer build;
    private Integer apartment;
    private String street;

    @ManyToOne
    @JsonBackReference
    private CityItem cityItem;

    @OneToOne
    @JsonBackReference
    private UserItem userItem;

    @OneToMany(mappedBy = "addressItem")
    @JsonManagedReference
    private List<EventItem> eventItems = new ArrayList<>();
}
