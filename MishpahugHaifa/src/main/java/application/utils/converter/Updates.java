package application.utils.converter;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import application.entities.EventEntity;
import application.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Updates extends ConverterBase implements IUpdates { //TODO: naming convention for update fields; //TODO отправка поддтверждения по почте
	
	@Override
	public void updateEvent(EventEntity eventEntity, HashMap<String, String> data) {
		if (data.containsKey("nameOfEvent")) { 
			System.out.println("Updates -> eventUpdate -> nameOfEvent -> new name = " + data.get("nameOfEvent"));
			eventEntity.setNameOfEvent(data.get("nameOfEvent"));
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

        //TODO остальные поля

    }
}
