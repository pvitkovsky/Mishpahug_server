package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cities")
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "countryEntity"})
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne                                  // Country of city
    @JsonBackReference
    private CountryEntity countryEntity;

    @OneToMany(mappedBy = "cityEntity")
    @JsonManagedReference
    private List<AddressEntity> addressEntities = new ArrayList<>();

    public CityEntity(String name, CountryEntity countryEntity, List<AddressEntity> addressEntities) {
        this.name = name;
        this.countryEntity = countryEntity;
        this.addressEntities = addressEntities;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryEntity=" + countryEntity +
                '}';
    }

}
