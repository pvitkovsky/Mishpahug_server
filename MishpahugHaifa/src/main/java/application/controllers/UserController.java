package application.controllers;

import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
import application.dto.UserDTODetail;
import application.entities.UserEntity;
import application.entities.UserSession;
import application.exceptions.ExceptionMishpaha;
import application.models.gender.IGenderModel;
import application.models.holyday.IHolyDayModel;
import application.models.kichentype.IKichenTypeModel;
import application.models.marriagestatus.IMaritalStatusModel;
import application.models.religion.IReligionModel;
import application.models.user.IUserModel;
import application.repositories.UserSessionRepository;
import com.querydsl.core.types.Predicate;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

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
    UserSessionRepository userSessionRepository;

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
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO){
        UserEntity userEntity = userModel.getByUsernameAndPassword(loginDTO.getUsername(), DigestUtils.md5Hex(loginDTO.getPassword()));
        if (userEntity == null){
            throw new RuntimeException("Incorrect password or username");
        }
        UserSession userSession = UserSession.builder()
                .userEntity(userEntity)
                .token(UUID.randomUUID().toString())
                .isValid(true)
                .build();
        userSessionRepository.save(userSession);
        return new LoginResponse(userSession.getToken());
    }

    @PostMapping(value = "/logout")
    public void logout(@RequestHeader(name = "Authorization", required = false) String token){
        UserSession userSession = userSessionRepository.findByTokenAndIsValidTrue(token);
        userSession.setIsValid(false);
        userSessionRepository.save(userSession);
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
