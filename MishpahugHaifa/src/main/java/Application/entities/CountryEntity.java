package Application.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="country")
//@Getter @Setter
//@NoArgsConstructor
//@EqualsAndHashCode(of = {"cityEntities" , "userEntities"})
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

    public List<CityEntity> getCityEntities() {
        return cityEntities;
    }

    public void setCityEntities(List<CityEntity> cityEntities) {
        this.cityEntities = cityEntities;
    }

    public CountryEntity() {
    }

    public CountryEntity(String name, List<CityEntity> cityEntities) {
        this.name = name;
        this.cityEntities = cityEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                cityEntities.equals(that.cityEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cityEntities);
    }
}

