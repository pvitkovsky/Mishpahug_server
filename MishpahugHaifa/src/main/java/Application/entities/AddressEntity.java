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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="address",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"city_of_address", "street", "building", "apartment"})
	})
@Getter @Setter
@ToString(exclude = {"eventEntities", "userEntities"})
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(of = {"cityEntity", "street", "building", "apartment"})
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_of_address")
    @JsonBackReference
    @Setter(AccessLevel.PACKAGE)
    private CityEntity cityEntity;
    
    @Column(name = "street")
    @Size(min = 4, max = 32, message = "maximum 32 symbols")
    private String street;
    
    @Column(name = "building")
    private Integer building;

    @Column(name = "apartment")
    private Integer apartment;

    @OneToMany(mappedBy = "addressEntity",  cascade = {CascadeType.MERGE, CascadeType.PERSIST}  , fetch = FetchType.LAZY, orphanRemoval = false) 
    @JsonBackReference
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
    private Set<UserEntity> userEntities;

    @OneToMany(mappedBy = "addressEntity",   cascade = {CascadeType.MERGE, CascadeType.PERSIST}  , fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonManagedReference
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
    private Set<EventEntity> eventEntities = new HashSet<>();

    /**
     * Adding a user to the address; 
     * @paramcity
     * @return
     */
    public boolean addUser(UserEntity userEntity) {
    	userEntity.setAddressEntity(this);
    	return userEntities.add(userEntity);
    }
    
    /**
     * User is not deleted once the address is merged;
     * @paramcity
     * @return
     */
    public boolean removeUser(UserEntity userEntity) {
    	return userEntities.remove(userEntity);
    }
    
    /**
     * Immutable wrapper over Users;
     * @return
     */
    public Set<UserEntity> getUsers() {
    	return Collections.unmodifiableSet(userEntities); 
    }
    
    
    /**
     * Adding a Event to the address; 
     * @paramcity
     * @return
     */
    public boolean addEvent(EventEntity eventEntity) {
    	eventEntity.setAddressEntity(this);
    	return eventEntities.add(eventEntity);
    }
    
    /**
     * Event is not deleted once the address is merged;
     * @paramcity
     * @return
     */
    public boolean removeEvent(EventEntity eventEntity) {
    	return eventEntities.remove(eventEntity);
    }
    
    /**
     * Immutable wrapper over Events;
     * @return
     */
    public Set<EventEntity> getEvents() {
    	return Collections.unmodifiableSet(eventEntities); 
    }
    

}
