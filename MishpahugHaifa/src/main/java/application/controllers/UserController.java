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
public class UserController implements IUserController {
	
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


    /* (non-Javadoc)
	 * @see application.controllers.IUserController#get(java.lang.Integer)
	 */
    @Override
	@GetMapping(value="/{id}")
    public UserEntity get(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.getById(id);
    }

    /* (non-Javadoc)
	 * @see application.controllers.IUserController#add(application.dto.UserDTO)
	 */
    @Override
	@PostMapping(value="/")
    public UserEntity add(@RequestBody UserDTO userDTO) throws ExceptionMishpaha {
        return userModel.add(new UserEntity(userDTO));
    }

    /* (non-Javadoc)
	 * @see application.controllers.IUserController#update(java.util.HashMap, java.lang.Integer)
	 */
    @Override
	@PutMapping(value="/{id}")
    public UserEntity update(@RequestBody HashMap<String, String> data,
                             @PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.update(id, data);
    }

    /* (non-Javadoc)
	 * @see application.controllers.IUserController#delete(java.lang.Integer)
	 */
    @Override
	@DeleteMapping(value = "/{id}")
    public UserEntity delete(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.deleteByID(id);
    }

    /* (non-Javadoc)
	 * @see application.controllers.IUserController#delete()
	 */
    @Override
	@DeleteMapping(value = "/")
    public void delete() throws ExceptionMishpaha {
        userModel.deleteAll();
    }

    /* (non-Javadoc)
	 * @see application.controllers.IUserController#setDataFromForm(application.dto.UserDTO)
	 */
    @Override
	@PostMapping(value="/addPage1")
    public void setDataFromForm(@RequestBody UserDTO data) throws ExceptionMishpaha{
        UserEntity userEntity = new UserEntity(data);
        userModel.add(userEntity);
    }

    /* (non-Javadoc)
	 * @see application.controllers.IUserController#setDataFromFormDetail(application.dto.UserDTODetail, java.lang.String)
	 */
    @Override
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
    
  /* (non-Javadoc)
 * @see application.controllers.IUserController#findAllByWebQuerydsl(com.querydsl.core.types.Predicate)
 */
@Override
@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public Iterable<UserEntity> findAllByWebQuerydsl(
	  @QuerydslPredicate(root = UserEntity.class) Predicate predicate) {
	    return userModel.getAll(predicate);
	}

}
