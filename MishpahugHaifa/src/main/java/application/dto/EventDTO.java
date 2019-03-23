package application.dto;

import application.entities.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "date", "time", "nameOfEvent", "userOwnerEntity" }) // business key;
@ToString(exclude = { "date", "time", "nameOfEvent", "userOwnerEntity" })
public class EventDTO {

	private LocalDate date;

	private LocalTime time;

	private String nameOfEvent;

	private  AddressEntity addressEntity;

	private UserEntity userOwnerEntity;
}
