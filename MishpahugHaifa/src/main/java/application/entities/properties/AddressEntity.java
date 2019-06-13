package application.entities.properties;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"city_of_address", "street", "building", "apartment"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"cityEntity", "street", "building", "apartment"})
@Slf4j
//https://projectlombok.org/features/Log
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_of_address")
    @JsonBackReference("cityOfAddress")
    @Setter(AccessLevel.PACKAGE)
    private CityEntity cityEntity;

    @Column(name = "street")
    @Size(min = 1, max = 128, message = "maximum 128 symbols")
    private String street;

    @Column(name = "building")
    private Integer building;

    @Column(name = "apartment")
    private Integer apartment;

    @Override
    public String toString() {
        return  cityEntity + "," + street + "," + building + "," + apartment;
    }
}
