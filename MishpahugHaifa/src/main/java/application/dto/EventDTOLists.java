package application.dto;

import application.entities.HoliDayEntity;
import application.entities.KitchenTypeEntity;
import application.entities.ReligionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventDTOLists {
    private List<HoliDayEntity> holiDayEntities;
    private List<ReligionEntity> religionEntities;
    private List<KitchenTypeEntity> kichenTypeEntities;
}
