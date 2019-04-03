package application.dto;

import application.entities.AddressEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
	private LocalDate date;
	private LocalTime time;
	private String nameOfEvent;
	private  AddressEntity addressEntity;
	private String holiday;
	private String kichenType;
}
