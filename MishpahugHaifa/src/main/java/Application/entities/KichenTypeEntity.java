package application.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kichentype",  uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" })})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@ToString
public class KichenTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

}
