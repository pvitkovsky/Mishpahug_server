package application.controllers;

import application.controllers.intarfaces.IUserController;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

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
    public UserEntity get(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.getById(id);
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
        UserSession userSessionOld = userSessionRepository.findByUserEntityAndIpAndUserAgentAndIsValidTrue(loginDTO.getUsername(),
                request.getRemoteAddr(),
                httpHeaders.get("user-agent").get(0));
        if (userSessionOld != null && userSessionOld.getLocalTime() == LocalTime.now()/* && userSessionOld.getLocalDate() == LocalDate.now()*/) {
            userSessionOld.setToken(UUID.randomUUID().toString());
            userSessionOld.setLocalDate(DateTime.now().toLocalDate());
            userSessionOld.setLocalTime(DateTime.now().toLocalTime());
            log.info("User Controller -> Update token");
            userSessionRepository.save(userSessionOld);
            return new LoginResponse(userSessionOld.getToken());
        }
        UserSession userSessionNew = UserSession.builder()
                .userEntity(userEntity.getUserName())
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
        UserSession userSession = userSessionRepository.findByTokenAndIsValidTrue(token);
        userSession.setIsValid(false);
        userSessionRepository.save(userSession);
    }

    @Override
    @PostMapping(value = "/register")
    public void add(@RequestBody UserDTO userDTO) throws ExceptionMishpaha {
        if (userModel.getByName(userDTO.getUserName()) != null) {
            throw new RuntimeException("Such user already exists");
        }
        if (!userDTO.getEncrytedPassword().equals(userDTO.getConfirmedPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        UserEntity userEntity = new UserEntity(userDTO);
        userModel.add(userEntity);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#update(java.util.HashMap, java.lang.Integer)
     */
    @Override
    @PutMapping(value = "/{id}")
    public UserEntity update(@RequestBody HashMap<String, String> data,
                             @PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.update(id, data);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#delete(java.lang.Integer)
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public UserEntity delete(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha {
        return userModel.deleteByID(id);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#delete()
     */
    @Override
    @DeleteMapping(value = "/")
    public void delete() throws ExceptionMishpaha {
        userModel.deleteAll();
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#setDataFromForm(application.dto.UserDTO)
     */
    @Override
    @PostMapping(value = "/addPage1")
    public void setDataFromForm(@RequestBody UserDTO data) throws ExceptionMishpaha {
        UserEntity userEntity = new UserEntity(data);
        userModel.add(userEntity);
    }

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#setDataFromFormDetail(application.dto.UserDTODetail, java.lang.String)
     */
    @Override
    @PostMapping(value = "/addPage2")
    public void setDataFromFormDetail(@RequestBody UserDTODetail data,
                                      @RequestParam(name = "username") String userName) throws ExceptionMishpaha {
        UserEntity userEntity = userModel.getByName(userName);
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
    public Iterable<UserEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = UserEntity.class) Predicate predicate) {
        return userModel.getAll(predicate);
    }

}
