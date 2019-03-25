package application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "marriage_status", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@ToString(exclude = "userEntities")
public class MarriageStatusEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "marriageStatusEntity", fetch = FetchType.LAZY, orphanRemoval = false)
	@JsonBackReference
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Set<UserEntity> userEntities = new HashSet<>();

	public boolean addUser(UserEntity userEntity) {
		userEntity.setMarriageStatusEntity(this);
		return userEntities.add(userEntity);
	}

	public boolean removeUser(UserEntity userEntity) {
    	userEntity.setMarriageStatusEntity(null);
    	return userEntities.remove(userEntity);
    }
}
