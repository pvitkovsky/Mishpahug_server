package Application.entities.values;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;

import Application.entities.UserItem;
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

	private UserItem userItemOwner; 
	private LocalDate date;
	private LocalTime time;
	private UserActions action;
	private String description;

	public enum UserActions {
		STATUS_CHANGE, EMAIL_CHANGE, ADDRESS_CHANGE,
	}
}
