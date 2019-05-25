package application.controllers;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import application.utils.Converter;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import application.controllers.interfaces.IUserController;
import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#get(java.lang.Integer)
     */
    @Override
    @GetMapping(value = "/{id}")
    public UserDTO get(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return new UserDTO(userModel.getById(id));
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#get(java.lang.Integer)
     */
    @Override
    @GetMapping(value = "/current")
    public UserDTO getByToken(HttpServletRequest request) throws ExceptionMishpaha {
    	String token = request.getHeader("Authorization");
    	UserSession session = userSessionRepository.findByTokenAndIsValidTrue(token);
    	return new UserDTO(userModel.getByUserName(session.getUserName()));//return new UserDTO(userModel);
    }


    @Override
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO, @RequestHeader HttpHeaders httpHeaders,
                               HttpServletRequest request) {
        log.info("User Controller -> Headers -> " + httpHeaders.get("user-agent"));
        log.info("User Controller -> Remote IP Address -> " + request.getRemoteAddr());

        UserEntity userEntity = userModel.getByUsernameAndPassword(loginDTO.getUsername(), DigestUtils.md5Hex(loginDTO.getPassword()));
        if (userEntity == null) {
            throw new RuntimeException("Incorrect password or username");
        }
        UserSession userSessionOld = userSessionRepository.findByUserNameAndIpAndUserAgentAndIsValidTrue(loginDTO.getUsername(),
                request.getRemoteAddr(),
                httpHeaders.get("user-agent").get(0));
        httpHeaders.forEach((key, value) -> {
            log.info("User Controller -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        if (userSessionOld != null) {
            userSessionOld.setToken(UUID.randomUUID().toString());
            userSessionOld.setLocalDate(DateTime.now().toLocalDate());
            userSessionOld.setLocalTime(DateTime.now().toLocalTime());
            log.info("User Controller -> Update token");
            userSessionRepository.save(userSessionOld);
            return new LoginResponse(userSessionOld.getToken());
        }
        UserSession userSessionNew = UserSession.builder()
                .userName(userEntity.getUserName())
                .token(UUID.randomUUID().toString())
                .ip(request.getRemoteAddr())
                .userAgent(httpHeaders.get("user-agent").get(0))
                .localDate(DateTime.now().toLocalDate())
                .localTime(DateTime.now().toLocalTime())
                .isValid(true)
                .build();
        userSessionRepository.save(userSessionNew);
        return new LoginResponse(userSessionNew.getToken());
    }

    @Override
    @PostMapping(value = "/logout")
    public void logout(@RequestHeader(name = "Authorization", required = false) String token) {
        if (token == null) throw new RuntimeException("Token is NULL");
        UserSession userSession = userSessionRepository.findByTokenAndIsValidTrue(token);
        if (userSession == null)  throw new RuntimeException("Token is incorrect");
        userSession.setIsValid(false);
        userSessionRepository.save(userSession);
    }

    @Override
    @PostMapping(value = "/register")
    public void add(@RequestBody UserDTO userDTO) throws ExceptionMishpaha {
        System.out.println("UserController -> Register -> UserDTO = " + userDTO);
        if (userModel.getByUserName(userDTO.getUserName()) != null) {
            throw new RuntimeException("Such user already exists");
        }
        if (!userDTO.getEncryptedPassword().equals(userDTO.getConfirmedPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        UserEntity userEntity = Converter.entityFromDTO(userDTO);
        userModel.add(userEntity);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#update(java.util.HashMap, java.lang.Integer)
     */
    //TODO: difficult choice between allowing and not allowing to update UserName; in addition: UserDTO can have null ID;  
    @Override
    @PutMapping(value = "/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO,
                             @PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
    	if(userDTO.getId() != id) {
    		throw new ExceptionMishpaha("Id in the UserDTO differs from the id in the path", null);
    	} 
        return new UserDTO(userModel.update(userDTO));
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#delete(java.lang.Integer)
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public UserDTO delete(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return new UserDTO(userModel.deleteByID(id));
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#delete()
     */
    @Override
    @DeleteMapping(value = "/")
    public void deleteAll() throws ExceptionMishpaha {
        userModel.deleteAll();
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#setDataFromForm(application.dto.UserDTO)
     */
    @Override
    @PostMapping(value = "/addPage") //TODO: not-restful name; better is viewPage1
    public void setDataFromForm(@RequestBody UserDTO data) throws ExceptionMishpaha {
        UserEntity userEntity = Converter.entityFromDTO(data);
        userModel.add(userEntity);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#setDataFromFormDetail(application.dto.UserDTODetail, java.lang.String)
     */
    @Override
    @PutMapping(value = "/addPage") //TODO: not-restful name; better is viewPage2
    public void setDataFromFormDetail(@RequestBody UserDTO data,
                                      @RequestParam(name = "username") String userName) throws ExceptionMishpaha {
        UserEntity userEntity = userModel.getByUserName(userName);
        userEntity.setGender(genderModel.getByName(data.getGender()));
        userEntity.setMaritalStatus(maritalStatusModel.getByName(data.getMaritalStatus()));
        userEntity.setReligion(religionModel.getByName(data.getReligion()));
        userEntity.setKitchenType(kichenTypeModel.getByName(data.getKichenType()));
        userModel.add(userEntity);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#findAllByWebQuerydsl(com.querydsl.core.types.Predicate)
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public List<UserDTO> findAllByWebQuerydsl(
            @QuerydslPredicate(root = UserEntity.class) Predicate predicate) {
        return Converter.userDTOListFromEntities(userModel.getAll(predicate));
    }

}
