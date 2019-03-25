package application.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "religion", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "name" })
@ToString(exclude = "userEntities")
public class ReligionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "religionEntity", fetch = FetchType.LAZY, orphanRemoval = false)
	@JsonBackReference
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Set<UserEntity> userEntities = new HashSet<>();

	public boolean addUser(UserEntity userEntity) {
		userEntity.setReligionEntity(this);
		return userEntities.add(userEntity);
	}

	public boolean removeUser(UserEntity userEntity) {
		userEntity.setReligionEntity(null);
		return userEntities.remove(userEntity);
	}
}
