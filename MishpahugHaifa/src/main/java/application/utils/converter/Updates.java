package application.utils.converter;

import application.entities.EventEntity;
import application.entities.properties.GenderEntity;
import application.entities.properties.KitchenTypeEntity;
import application.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class Updates extends ConverterBase implements IUpdates {
	
	@Override
	public void updateEvent(EventEntity eventEntity, HashMap<String, String> data) {
		if (data.containsKey("nameOfEvent")) { // TODO: naming convention for update fields;
			log.info("Updates -> eventUpdate -> nameOfEvent -> new name = " + data.get("nameOfEvent"));
			eventEntity.setNameOfEvent(data.get("nameOfEvent"));
			// не меняется дата, время и владелец
            //меняются кухня, имя, адрес
		}
	}

	@Override
	public void updateUser(UserEntity userEntity, HashMap<String, String> data){
    	System.out.println(data);
    	 if (data.containsKey("firstname")) { //TODO: naming convention for update fields;
             log.info("UserConverter -> Update -> firstname -> new firstname = " + data.get("firstname"));
             userEntity.setFirstName(data.get("firstname"));//TODO отправка поддтверждения по почте
         }
        if (data.containsKey("email")) {
            log.info("UserConverter -> Update -> email -> new email = " + data.get("email"));
            userEntity.setEMail(data.get("email"));//TODO отправка поддтверждения по почте
        }
        if (data.containsKey("gender")) {
            log.info("UserConverter -> Update -> gender -> new gender = " + data.get("gender"));
            GenderEntity genderEntity = genderRepository.getByName(data.get("gender"));
            userEntity.setGender(genderEntity);
        }
        if (data.containsKey("kitchentype")) {
            log.info("UserConverter -> Update -> kitchentype -> new kitchentype = " + data.get("kitchentype"));
            KitchenTypeEntity kitchenTypeEntity = kichenTypeRepository.getByName(data.get("kitchentype"));
            userEntity.setKitchenType(kitchenTypeEntity);
        }

        //TODO остальные поля

    }
}
