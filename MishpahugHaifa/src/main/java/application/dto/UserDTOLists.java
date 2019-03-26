package application.dto;

import application.entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTOLists {
    private List<ReligionEntity> religionEntities;
    private List<KitchenTypeEntity> kichenTypeEntities;
    private List<GenderEntity> genderEntities;
    private List<MaritalStatusEntity> maritalStatusEntities;
}
