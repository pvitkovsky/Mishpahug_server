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

    @Column(name = "build")
    private Integer build;

    @Column(name = "apartment")
    private Integer apartment;

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JsonBackReference
    private CityEntity cityEntity;

    @OneToOne(mappedBy = "addressEntity")
    @JsonBackReference
    private UserEntity userEntity;

    @OneToMany(mappedBy = "addressEntity")
    @JsonManagedReference
    private List<EventEntity> eventEntities = new ArrayList<>();
}
