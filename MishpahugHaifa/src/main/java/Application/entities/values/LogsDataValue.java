package Application.entities.values;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class LogsDataValue {

	//TODO: Bidirectional private UserEntity userItemOwner;
	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "action")
	private UserActions action;

	@Column(name = "description")
	private String description;

	public enum UserActions {
		STATUS_CHANGE, EMAIL_CHANGE, ADDRESS_CHANGE,
	}
}
