package application.entities.properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "holyday", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HoliDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true) //Unidirectional;
    private ReligionEntity religionEntity;

    @Column(name = "description")
    private String description;

}
