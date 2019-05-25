package application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "country_of_city"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "countryEntity"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Slf4j
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_of_city")
    @JsonBackReference("countryOfCity")
    @Setter(AccessLevel.PACKAGE)
    private CountryEntity countryEntity;

    @OneToMany(mappedBy = "cityEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("cityOfAddress")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<AddressEntity> addressEntities = new HashSet<>();

    /**
     * Adding an address to the city
     *
     * @return
     * @paramcity
     */
    public boolean addAddress(AddressEntity address) {
        address.setCityEntity(this);
        return addressEntities.add(address);
    }

    /**
     * Address is deleted once the city is merged;
     *
     * @return
     * @paramcity
     */
    public boolean removeAddress(AddressEntity address) {
        return addressEntities.remove(address);
    }

    /**
     * Immutable wrapper;
     *
     * @return
     */
    public Set<AddressEntity> getAddresses() {
        return Collections.unmodifiableSet(addressEntities);
    }

    /**
     * Clearing all addresses
     *
     * @return
     */
    public void clearAddresses() {
        addressEntities.clear();
    }

    @Override
    public String toString() {
        return name  + "," + countryEntity.getName();
    }
}
