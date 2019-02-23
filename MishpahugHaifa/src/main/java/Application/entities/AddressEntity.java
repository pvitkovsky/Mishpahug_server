package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="address")
//@Getter @Setter
@ToString
//@AllArgsConstructor //do construction without userEntity
//@NoArgsConstructor
//@EqualsAndHashCode(of = "userEntity")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "build")
    private Integer build;

    @Column(name = "apartment")
    private Integer apartment;

    @Column(name = "street")
    @Size(min = 4, max = 32, message = "maximum 32 symbols")
    private String street;

    @ManyToOne
    @JsonBackReference
    private CityEntity cityEntity;

    @OneToOne//(mappedBy = "addressEntity")
    @JsonBackReference
    private UserEntity userEntity;

    @OneToMany(mappedBy = "addressEntity")
    @JsonManagedReference
    private List<EventEntity> eventEntities = new ArrayList<>();

    public AddressEntity() {
    }

    public AddressEntity(Integer build, Integer apartment, String street, CityEntity cityEntity, UserEntity userEntity, List<EventEntity> eventEntities) {
        this.build = build;
        this.apartment = apartment;
        this.street = street;
        this.cityEntity = cityEntity;
        this.userEntity = userEntity;
        this.eventEntities = eventEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuild() {
        return build;
    }

    public void setBuild(Integer build) {
        this.build = build;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<EventEntity> getEventEntities() {
        return eventEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id.equals(that.id) &&
                build.equals(that.build) &&
                apartment.equals(that.apartment) &&
                street.equals(that.street) &&
                cityEntity.equals(that.cityEntity) &&
                userEntity.equals(that.userEntity) &&
                eventEntities.equals(that.eventEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, build, apartment, street, cityEntity, userEntity, eventEntities);
    }

    public void setEventEntities(List<EventEntity> eventEntities)
    {
        this.eventEntities = eventEntities;
    }
}
