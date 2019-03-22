package application.dto;

import application.entities.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
