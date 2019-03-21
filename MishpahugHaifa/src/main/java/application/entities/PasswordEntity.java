package application.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PasswordEntity {

	@Id
	Long id;
}
