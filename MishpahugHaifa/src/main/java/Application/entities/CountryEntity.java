package Application.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "name" )
    private String name;

    @OneToMany(mappedBy = "countryEntity")
    @JsonManagedReference
    private List<CityEntity> cityEntities = new ArrayList<>();

}

