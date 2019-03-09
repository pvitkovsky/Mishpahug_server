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
@Getter @Setter
@ToString
@AllArgsConstructor //do construction without userEntity
@NoArgsConstructor
@EqualsAndHashCode(of = "userEntity")
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
    //TODO: safe bidirectional setter	
    private CityEntity cityEntity;

    @OneToOne//(mappedBy = "addressEntity")
    @JsonBackReference
    //TODO: safe bidirectional setter
    private UserEntity userEntity;

    @OneToMany(mappedBy = "addressEntity")
    @JsonManagedReference
    //TODO: safe bidirectional getter/setter
    private List<EventEntity> eventEntities = new ArrayList<>();

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

}
