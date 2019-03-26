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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@ToString
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
    @Size(min = 1, max = 128, message = "maximum 128 symbols")
    private String street;
    
    @Column(name = "building")
    private Integer building;

    @Column(name = "apartment")
    private Integer apartment;
}
