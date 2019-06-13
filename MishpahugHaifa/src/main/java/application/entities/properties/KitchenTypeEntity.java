package application.entities.properties;

import application.entities.interfaces.NamedProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "kichentype", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class KitchenTypeEntity implements NamedProperty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	public KitchenTypeEntity(String name) {
		super();
		this.name = name;
	}

}
