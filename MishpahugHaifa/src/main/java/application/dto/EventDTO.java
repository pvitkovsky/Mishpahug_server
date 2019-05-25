package application.dto;

import application.entities.AddressEntity;
import application.entities.EventEntity;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private String addressCountry;
    private String addressCity;
    private String addressStreet;
    private Integer addressBuild;
    private Integer addressApartment;
    private String holiday;
    private String holidayDescription;
    private String kichenType;


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
        this.holidayDescription =eventEntity.getHoliDay() ==null ? "" : eventEntity.getHoliDay().getDescription();
        this.kichenType =eventEntity.getKitchenType() ==null ? "" : eventEntity.getKitchenType().getName();

    }
}
