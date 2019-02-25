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
//@Getter @Setter
//@ToString
//@NoArgsConstructor
//@EqualsAndHashCode(of = "userEntities")
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

    public CityEntity() {
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryEntity=" + countryEntity +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }

    public List<AddressEntity> getAddressEntities() {
        return addressEntities;
    }

    public void setAddressEntities(List<AddressEntity> addressEntities) {
        this.addressEntities = addressEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                countryEntity.equals(that.countryEntity) &&
                addressEntities.equals(that.addressEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryEntity, addressEntities);
    }
}
