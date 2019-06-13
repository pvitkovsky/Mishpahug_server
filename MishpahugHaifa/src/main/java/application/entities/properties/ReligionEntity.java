package application.entities.properties;

import application.entities.interfaces.NamedProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "religion", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReligionEntity implements NamedProperty{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "name")
    private String name;
	
    public ReligionEntity(String name) {
		super();
		this.name = name;
	}
}
