package application.controllers;

import application.dto.UserDTO;
import application.dto.UserDTODetail;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.gender.IGenderModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.marriagestatus.IMaritalStatusModel;
import application.models.religion.IReligionModel;
import application.models.user.IUserModel;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

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

//TODO: clarify please
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


    @GetMapping(value="/{id}")
    public UserEntity get(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.getById(id);
    }

    @PostMapping(value="/register")
    public void add(@RequestBody UserDTO userDTO) throws ExceptionMishpaha {
        if (userModel.getByName(userDTO.getUserName()) != null){
            throw new RuntimeException("Such user already exists");
        }
        if (!userDTO.getEncrytedPassword().equals(userDTO.getConfirmedPassword())){
            throw new RuntimeException("Passwords do not match");
        }
        UserEntity userEntity = new UserEntity(userDTO);
        userModel.add(userEntity);
    }

    @PutMapping(value="/{id}")
    public UserEntity update(@RequestBody HashMap<String, String> data,
                             @PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.update(id, data);
    }

    @DeleteMapping(value = "/{id}")
    public UserEntity delete(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.deleteByID(id);
    }

    @DeleteMapping(value = "/")
    public void delete() throws ExceptionMishpaha {
        userModel.deleteAll();
    }

    @PostMapping(value="/addPage1")
    public void setDataFromForm(@RequestBody UserDTO data) throws ExceptionMishpaha{
        UserEntity userEntity = new UserEntity(data);
        userModel.add(userEntity);
    }

    @PostMapping(value="/addPage2")
    public void setDataFromFormDetail(@RequestBody UserDTODetail data,
                                      @RequestParam(name = "username") String userName) throws ExceptionMishpaha{
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
	    return userModel.getAll(predicate);
	}

}
