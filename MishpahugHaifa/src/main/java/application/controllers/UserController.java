package application.controllers;

import application.dto.*;
import application.entities.UserEntity;
import application.models.gender.IGenderModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.marriagestatus.IMaritalStatusModel;
import application.models.religion.IReligionModel;
import application.models.user.IUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    IMaritalStatusModel maritalStatusModel;

    @Autowired
    IHolyDayModel holyDayModel;

    @GetMapping(value="/")
    public List<UserEntity> get(){
        return userModel.getAll();
    }

    @GetMapping(value="/getbyreligion")
    public List<UserEntity> getByReligion(@RequestParam(name = "religion") String religion){
        return userModel.getByReligion(religion);
    }

    @GetMapping(value="/getbykitchen")
    public List<UserEntity> getByKitchenType(@RequestParam(name = "kitchentype") String kitchenType){
        return userModel.getByKitchenType(kitchenType);
    }

    @GetMapping(value="/getbygender")
    public List<UserEntity> getByGender(@RequestParam(name = "gender") String gender){
        return userModel.getByGender(gender);
    }

    @GetMapping(value="/getbymaritalstatus")
    public List<UserEntity> getByMaritalStatus(@RequestParam(name = "maritalstatus") String maritalStatus){
        return userModel.getByMaritalStatus(maritalStatus);
    }

    @GetMapping(value="/getbyfilter")
    public List<UserEntity> getByFilter(@RequestBody HashMap<String, String> data){
        return userModel.getByFilter(data);
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
        userEntity.setGender(genderModel.getByName(data.getGender()));
        userEntity.setMaritalStatus(maritalStatusModel.getByName(data.getMaritalStatus()));
        userEntity.setReligion(religionModel.getByName(data.getReligion()));
        userEntity.setKitchenType(kichenTypeModel.getByName(data.getKichenType()));
        userModel.add(userEntity);
    }

}
