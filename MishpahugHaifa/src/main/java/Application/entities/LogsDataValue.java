package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;

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

	private LocalDate date;
	private LocalTime time;
	private UserActions action;
	private String description;

	public enum UserActions {
		STATUS_CHANGE, EMAIL_CHANGE, ADDRESS_CHANGE,
	}
}
