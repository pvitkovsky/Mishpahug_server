package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Integer build;
    private Integer apartment;
    private String street;

    @ManyToOne
    @JsonBackReference
    private CityEntity cityEntity;

    @OneToOne
    @JsonBackReference
    private UserEntity userEntity;

    @OneToMany(mappedBy = "addressItem")
    @JsonManagedReference
    private List<EventEntity> eventEntities = new ArrayList<>();
}
