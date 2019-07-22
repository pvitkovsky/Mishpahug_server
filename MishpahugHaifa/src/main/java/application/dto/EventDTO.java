package application.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import application.models.event.EventEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String nameOfEvent;
    private Boolean isEnabled;
    private String address;
    private String holiday; 
    private Integer ownerId;
    private List<Integer> guestIds; 


    public EventDTO(EventEntity eventEntity) {
        super();
        this.id = eventEntity.getId();
        this.date = eventEntity.getDate();
        this.time = eventEntity.getTime();
        this.nameOfEvent = eventEntity.getNameOfEvent();
        this.ownerId = eventEntity.getUserEntityOwner().getId();
        this.isEnabled = eventEntity.isEnabled();
    }
}
