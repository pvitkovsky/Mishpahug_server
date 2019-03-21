package application.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "holyday", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@ToString
public class HoliDayEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "name")
	private String name;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JsonManagedReference //Unidirectional;
	private ReligionEntity religionEntity;

	@Column(name = "description")
	private String description;

}
