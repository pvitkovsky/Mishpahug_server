 package application.entities;

 import application.entities.interfaces.NamedProperty;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import lombok.*;

 import javax.persistence.*;

@Entity
@Table(name = "gender", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GenderEntity implements NamedProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

	public GenderEntity(String name) {
		super();
		this.name = name;
	}
}
