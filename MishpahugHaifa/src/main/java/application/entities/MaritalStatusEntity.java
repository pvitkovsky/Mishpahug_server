package application.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "marriage_status", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@ToString
public class MaritalStatusEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

}
