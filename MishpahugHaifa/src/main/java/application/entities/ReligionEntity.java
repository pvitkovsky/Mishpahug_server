package application.entities;

import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "religion", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "name" })
@ToString
public class ReligionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;
}
