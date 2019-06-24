package application.dto;

import application.entities.EventEntity;
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
    private String holiday; // why do we need 2 fields for this?
    private String holidayDescription; // why do we need 2 fields for this?
    private String kichenType;
    private Integer ownerId;


    public EventDTO(EventEntity eventEntity) {
        super();
        this.id = eventEntity.getId();
        this.addressCountry = eventEntity.getAddressEntity() == null ? "" : eventEntity.getAddressEntity().getCityEntity().getCountryEntity().getName();
        this.addressCity = eventEntity.getAddressEntity() == null ? "" : eventEntity.getAddressEntity().getCityEntity().getName();
        this.addressStreet = eventEntity.getAddressEntity() == null ? "" : eventEntity.getAddressEntity().getStreet();
        this.addressBuild = eventEntity.getAddressEntity() == null ? 0 : eventEntity.getAddressEntity().getBuilding();
        this.addressApartment = eventEntity.getAddressEntity() == null ? 0 : eventEntity.getAddressEntity().getApartment();
        this.date = eventEntity.getDate();
        this.time = eventEntity.getTime();
        this.nameOfEvent = eventEntity.getNameOfEvent();
        this.holiday = eventEntity.getHoliDay() ==null ? "" : eventEntity.getHoliDay().getName();
        this.holidayDescription = eventEntity.getHoliDay() ==null ? "" : eventEntity.getHoliDay().getDescription();
        this.kichenType = eventEntity.getKitchenType() ==null ? "" : eventEntity.getKitchenType().getName();
        this.ownerId = eventEntity.getUserEntityOwner().getId();
    }
}
