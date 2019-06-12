package dto;

import entities.EventEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String nameOfEvent;
    private String addressCountry;
    private String addressCity;
    private String addressStreet;
    private Integer addressBuild;
    private Integer addressApartment;
    private String holiday;
    private String holidayDescription;
    private String owner;
    private String kichenType;
    private List<String> guests; 


}
