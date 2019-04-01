package application.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="cities", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"name", "country_of_city"})
	})
@Getter @Setter
@ToString(exclude = "addressEntities")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "countryEntity"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "country_of_city")
    @JsonBackReference
    @Setter(AccessLevel.PACKAGE) 
    private CountryEntity countryEntity;

    @OneToMany(mappedBy = "cityEntity",  cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference 
    @Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
    private Set<AddressEntity> addressEntities = new HashSet<>();
    
    /**
     * Adding an address to the city
     * @paramcity
     * @return
     */
    public boolean addAddress(AddressEntity address) {
    	address.setCityEntity(this);
    	return addressEntities.add(address);
    }
    
    /**
     * Address is deleted once the city is merged;
     * @paramcity
     * @return
     */
    public boolean removeAddress(AddressEntity address) {
    	return addressEntities.remove(address);
    }
    
    /**
     * Immutable wrapper;
     * @return
     */
    public Set<AddressEntity> getAddresses() {
    	return Collections.unmodifiableSet(addressEntities); 
    }
    
    /**
     * Clearing all addresses
     * @return
     */
    public void clearAddresses(){
    	addressEntities.clear();
    }

}
