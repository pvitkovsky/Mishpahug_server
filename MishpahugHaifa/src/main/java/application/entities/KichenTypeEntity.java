package application.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kichentype",  uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" })})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@ToString(exclude = "userEntities")
public class KichenTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "kichenTypeEntity", fetch = FetchType.LAZY, orphanRemoval = false)
	@JsonBackReference
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Set<UserEntity> userEntities = new HashSet<>();

	public boolean addUser(UserEntity userEntity) {
		userEntity.setKichenTypeEntity(this);
		return userEntities.add(userEntity);
	}

	public boolean removeUser(UserEntity userEntity) {
		userEntity.setKichenTypeEntity(null);
		return userEntities.remove(userEntity);
	}
}
