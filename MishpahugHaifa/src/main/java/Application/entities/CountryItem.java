package Application.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="country")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"cityItems" , "userItems"})
@ToString
public class CountryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "countries_cities")
    @JsonManagedReference
    private List<CityItem> cityItems = new ArrayList<>();

    @OneToMany(mappedBy = "countries_users")
    @JsonManagedReference
    private List<UserItem> userItems = new ArrayList<>();


}

