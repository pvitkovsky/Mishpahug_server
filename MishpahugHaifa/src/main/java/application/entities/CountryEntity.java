package application.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="country", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"name"})
	})
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
@ToString(exclude = "cityEntities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "countryEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("countryOfCity")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<CityEntity> cityEntities = new HashSet<>();

    /**
     * Adding a city to the country
     * @param city
     * @return
     */
    public boolean addCity(CityEntity city) {
    	city.setCountryEntity(this);
    	return cityEntities.add(city);
    }
    
    /**
     * City is deleted once the country is merged;
     * @param city
     * @return
     */
    public boolean removeCity(CityEntity city) {
    	return cityEntities.remove(city);
    }
    
    /**
     * Immutable wrapper;
     * @return
     */
    public Set<CityEntity> getCities() {
    	return Collections.unmodifiableSet(cityEntities); 
    }
    
    
}

