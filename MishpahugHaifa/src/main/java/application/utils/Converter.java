package application.utils;

import application.dto.UserDTO;
import application.entities.*;
import application.repositories.GenderRepository;
import application.repositories.KichenTypeRepository;
import application.repositories.MaritalStatusRepository;
import application.repositories.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Converter {

    @Autowired
    static GenderRepository genderRepository;

    @Autowired
    static KichenTypeRepository kichenTypeRepository;

    @Autowired
    static ReligionRepository religionRepository;

    @Autowired
    static MaritalStatusRepository maritalStatusRepository;

    public static List<UserDTO> userDTOListFromEntities(List<UserEntity> data){
        List<UserDTO> res = new ArrayList<>();
        for (UserEntity x:data
             ) {
            res.add(new UserDTO(x));
        }
        return res;
    }

    public static List<String> kitchenTypestoStringList(List<KitchenTypeEntity> data){
        List<String> res = new ArrayList<>();
        for (KitchenTypeEntity x:data
             ) {
            res.add(x.getName());
        }
        return res;
    }

    public static List<String> ReligionstoStringList(List<ReligionEntity> data){
        List<String> res = new ArrayList<>();
        for (ReligionEntity x:data
        ) {
            res.add(x.getName());
        }
        return res;
    }

    public static List<String> MaritalStatusetoStringList(List<MaritalStatusEntity> data){
        List<String> res = new ArrayList<>();
        for (MaritalStatusEntity x:data
        ) {
            res.add(x.getName());
        }
        return res;
    }

    public static List<String> GenderstoStringList(List<GenderEntity> data){
        List<String> res = new ArrayList<>();
        for (GenderEntity x:data
        ) {
            res.add(x.getName());
        }
        return res;
    }

    public static UserEntity entityFromDTO(UserDTO data) {
        UserEntity res = new UserEntity(data.getUserName(), data.getEMail());
        res.setFirstName(data.getFirstName());
        res.setLastName(data.getLastName());
        res.setEncrytedPassword(data.getEncryptedPassword());
        res.setPhoneNumber(data.getPhoneNumber());
        res.setDateOfBirth(data.getDayOfBirth());
        res.setGender(genderRepository.getByName(data.getGender()));
        res.setKitchenType(kichenTypeRepository.getByName(data.getKichenType()));
        res.setReligion(religionRepository.getByName(data.getReligion()));
        res.setMaritalStatus(maritalStatusRepository.getByName(data.getMaritalStatus()));
        return res;
    }
}
