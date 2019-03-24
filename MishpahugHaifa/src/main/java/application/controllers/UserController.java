package application.controllers;

import application.dto.*;
import application.models.gender.IGenderModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.marriagestatus.IMarriageStatusModel;
import application.models.religion.IReligionModel;
import application.models.user.IUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value="/getall")
    public UserDTOLists getDataForAddForm(){
        UserDTOLists userDTOLists = new UserDTOLists();
        userDTOLists.setKichenTypeEntities(kichenTypeModel.getAll());
        userDTOLists.setReligionEntities(religionModel.getAll());
        userDTOLists.setGenderEntities(genderModel.getAll());
        userDTOLists.setMarriageStatusEntities(marriageStatusModel.getAll());
        return userDTOLists;

    }
    @PostMapping(value="/addPage2")
    public UserDTODetail setDataFromFormDetail(){
        return null;

    }

    @PostMapping(value="/addPage1")
    public UserDTO setDataFromForm(){
        return null;

    }
}
