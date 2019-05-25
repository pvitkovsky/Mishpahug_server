package application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private LocalDate date;
    private LocalTime time;
    private String nameOfEvent;
    private String addressEntity;
    private String holiday;
    private String kichenType;
}
