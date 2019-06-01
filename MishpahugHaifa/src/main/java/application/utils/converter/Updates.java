package application.utils.converter;

import application.entities.EventEntity;
import application.entities.GenderEntity;
import application.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
@Slf4j
public class Updates extends ConverterBase {
    public static void updateEvent(EventEntity eventEntity, HashMap<String, String> data){
        if (data.containsKey("nameOfEvent")) {
            log.info("Updates -> eventUpdate -> nameOfEvent -> new name = " + data.get("nameOfEvent"));
            eventEntity.setNameOfEvent(data.get("nameOfEvent"));
        }
    }

    public void updateUser(UserEntity userEntity, HashMap<String, String> data){
        if (data.containsKey("email")) {
            log.info("UserConverter -> Update -> email -> new email = " + data.get("email"));
            userEntity.setEMail(data.get("email"));//TODO отправка поддтверждения по почте
        }
        if (data.containsKey("gender")) {
            log.info("UserConverter -> Update -> gender -> new gender = " + data.get("gender"));
            GenderEntity genderEntity = genderRepository.getByName(data.get("gender"));
            userEntity.setGender(genderEntity);
        }
        
        //TODO остальные поля

    }
}
