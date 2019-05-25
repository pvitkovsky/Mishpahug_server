package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

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
