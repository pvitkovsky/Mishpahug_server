package application.utils.converter;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.entities.properties.GenderEntity;
import application.entities.properties.KitchenTypeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class Updates extends ConverterBase implements IUpdates { //TODO: naming convention for update fields; //TODO отправка поддтверждения по почте
	
	@Override
	public void updateEvent(EventEntity eventEntity, HashMap<String, String> data) {
		if (data.containsKey("nameOfEvent")) { 
			System.out.println("Updates -> eventUpdate -> nameOfEvent -> new name = " + data.get("nameOfEvent"));
			eventEntity.setNameOfEvent(data.get("nameOfEvent"));
		}
        if (data.containsKey("kitchentype")) {
            log.info("UserConverter -> Update -> kitchentype -> new kitchentype = " + data.get("kitchentype"));
            KitchenTypeEntity kitchenTypeEntity = kichenTypeRepository.getByName(data.get("kitchentype"));
            if (kitchenTypeEntity != null) eventEntity.setKitchenType(kitchenTypeEntity);
        }
	}

	@Override
	public void updateUser(UserEntity userEntity, HashMap<String, String> data){ 
    	System.out.println(data);
    	 if (data.containsKey("firstName")) {
             log.info("UserConverter -> Update -> firstName -> new firstname = " + data.get("firstName"));
             userEntity.setFirstName(data.get("firstName"));
         }
    	 if (data.containsKey("lastName")) { 
             log.info("UserConverter -> Update -> lastName -> new firstname = " + data.get("lastName"));
             userEntity.setLastName(data.get("lastName"));
         }
        if (data.containsKey("email")) {
            log.info("UserConverter -> Update -> email -> new email = " + data.get("email"));
            userEntity.setEMail(data.get("email"));
        }
        if (data.containsKey("gender")) {
            log.info("UserConverter -> Update -> gender -> new gender = " + data.get("gender"));
            GenderEntity genderEntity = genderRepository.getByName(data.get("gender"));
            if (genderEntity != null) userEntity.setGender(genderEntity);
        }
        if (data.containsKey("kitchentype")) {
            log.info("UserConverter -> Update -> kitchentype -> new kitchentype = " + data.get("kitchentype"));
            KitchenTypeEntity kitchenTypeEntity = kichenTypeRepository.getByName(data.get("kitchentype"));
            if (kitchenTypeEntity != null) userEntity.setKitchenType(kitchenTypeEntity);
        }

        //TODO остальные поля

    }
}
