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
@EqualsAndHashCode(of = {"cityEntities" , "userEntities"})
@ToString
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "countryItem")
    @JsonManagedReference
    private List<CityEntity> cityEntities = new ArrayList<>();

    @OneToMany(mappedBy = "countryItem")
    @JsonManagedReference
    private List<UserEntity> userEntities = new ArrayList<>();


}

