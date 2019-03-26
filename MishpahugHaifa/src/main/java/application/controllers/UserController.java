package application.controllers;

import application.dto.*;
import application.entities.UserEntity;
import application.models.gender.IGenderModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.marriagestatus.IMarriageStatusModel;
import application.models.religion.IReligionModel;
import application.models.user.IUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IReligionModel religionModel;

    @Autowired
    IKichenTypeModel kichenTypeModel;

    @Autowired
    IUserModel userModel;

    @Autowired
    IGenderModel genderModel;

    @Autowired
    IMarriageStatusModel marriageStatusModel;

    @GetMapping(value="/getlists")
    public UserDTOLists getDataForAddForm(){
        UserDTOLists userDTOLists = new UserDTOLists();
        userDTOLists.setKichenTypeEntities(kichenTypeModel.getAll());
        userDTOLists.setReligionEntities(religionModel.getAll());
        userDTOLists.setGenderEntities(genderModel.getAll());
        userDTOLists.setMarriageStatusEntities(marriageStatusModel.getAll());
        return userDTOLists;

    }

    @GetMapping(value="/getbygender")
    public List<UserEntity> getByGender(@RequestParam(name = "gender") String gender){
        return userModel.getByGender(gender);
    }

    //TODO дописать фильтр для полей с сущностями

    @PostMapping(value="/addPage1")
    public void setDataFromForm(@RequestBody UserDTO data){
        UserEntity userEntity = new UserEntity(data);
        userModel.add(userEntity);
    }

    @PostMapping(value="/addPage2")
    public void setDataFromFormDetail(@RequestBody UserDTODetail data,
                                      @RequestParam(name = "username") String userName){
        UserEntity userEntity = userModel.getByName(userName);
        userEntity.setGenderEntity(genderModel.getByName(data.getGender()));
        marriageStatusModel.getByName(data.getMarriageStatus()).addUser(userEntity);
        userEntity.setReligionEntity(religionModel.getByName(data.getReligion()));
        userEntity.setKichenTypeEntity(kichenTypeModel.getByName(data.getKichenType()));
        userModel.add(userEntity);
    }


}
