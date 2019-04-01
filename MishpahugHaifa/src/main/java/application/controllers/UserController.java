package application.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import application.dto.UserDTO;
import application.dto.UserDTODetail;
import application.entities.UserEntity;
import application.models.gender.IGenderModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.marriagestatus.IMaritalStatusModel;
import application.models.religion.IReligionModel;
import application.models.user.IUserModel;
import application.repositories.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired 
	UserRepository userRepo;

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

//    @GetMapping(value="/{action}")
//    public void getDataForAddForm(@PathVariable(value = "action", required = false) String action){
//        if (action != null){
//                switch (action) {
//                    case "lists": {
//
//                        break;
//                    }
//                }
//        }
//    }

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
    
    @RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public Iterable<UserEntity> findAllByWebQuerydsl(
	  @QuerydslPredicate(root = UserEntity.class) Predicate predicate) {
	    return userRepo.findAll(predicate);
	}

}
