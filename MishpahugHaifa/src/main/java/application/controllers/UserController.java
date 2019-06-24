
package application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import application.dto.EventDTO;
import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
import application.models.event.EventEntity;
import application.models.event.IEventModel;
import application.models.relation.IRelationModel;
import application.models.relation.SubscriptionEntity;
import application.models.user.IUserModel;
import application.models.user.UserEntity;
import application.models.user.UserSession;
import application.models.user.UserSessionRepository;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/user")
public class UserController implements IUserController {

    @Autowired
    IUserModel userModel;
    
    @Autowired
    UserSessionRepository userSessionRepository;

    @Autowired
    IRelationModel relationModel;

    @Autowired
    IEventModel eventModel;
    
    @Autowired
    IConverter<UserEntity, UserDTO> converter;

    @Autowired
    IConverter<EventEntity, EventDTO> converterEvent;


    @Override // SUBSCRIPTIONS
    @GetMapping(value = "/{id}/subscribes") // re-wrapping from Relation;		/*inter-aggregate query*/
    public List<EventDTO> getEventsById(@RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request, @PathVariable(value = "id") Integer id) {
    	List<EventEntity> subscribedEvents = relationModel.getSubscribedEvents(id);
        return converterEvent.DTOListFromEntities(subscribedEvents);
    }

    @Override // OWNER
    @GetMapping(value = "/{id}/events") // direct o2m connection; 		/*inter-aggregate query*/
    public List<EventDTO> getEventsByOwnerId(@RequestHeader HttpHeaders httpHeaders,
                                             HttpServletRequest request, @PathVariable(value = "id") Integer id) {
        List<EventEntity> ownedEvents = eventModel.getByOwner(userModel.getById(id).getUserName());
        return converterEvent.DTOListFromEntities(ownedEvents);
    }

    @Override
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request, @RequestBody LoginDTO loginDTO) throws FailedLoginException {
        UserEntity userEntity = userModel.getByUsernameAndPassword(loginDTO.getUsername(), DigestUtils.md5Hex(loginDTO.getPassword()));
        if (userEntity == null) {
            throw new FailedLoginException();
        }
        UserSession userSessionOld = userSessionRepository.findByUserNameAndIpAndUserAgentAndIsValidTrue(loginDTO.getUsername(),
                request.getRemoteAddr(),
                httpHeaders.get("user-agent").get(0));
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
    public void add(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, @RequestBody UserDTO userDTO) {
         if (userModel.getByUserName(userDTO.getUserName()) != null) {
             throw new UsernameNotFoundException("Error");
         }
         if (!userDTO.getEncryptedPassword().equals(userDTO.getConfirmedPassword())) {
            throw new RuntimeException("Passwords do not match");
         }
        UserEntity userEntity = converter.entityFromDTO(userDTO);
        userModel.add(userEntity);
    }

    
    @Override
    @GetMapping(value = "/{id}")
    public UserDTO get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(value = "id") Integer id) {
        return new UserDTO(userModel.getById(id));
    }
    
    @Override
    @GetMapping(value = "/current")		
    public UserDTO getByToken(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
    	String token = request.getHeader("Authorization");
        UserSession session = userSessionRepository.findByTokenAndIsValidTrue(token);
    	return new UserDTO(userModel.getByUserName(session.getUserName())); //TODO: converter here?
    }
    
    @Override
	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public List<UserDTO> findAllByWebQuerydsl(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@QuerydslPredicate(root = UserEntity.class) Predicate predicate) {
        return converter.DTOListFromEntities(userModel.getAll(predicate));
    }
    
    @Override
    @PutMapping(value = "/{id}")
    public UserDTO update(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody HashMap<String, String> data, @PathVariable(value = "id") Integer id) {      
        return new UserDTO(userModel.update(id, data));
    }

    
    @Override
    @DeleteMapping(value = "/{id}")
    public UserDTO delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(value = "id") Integer id) {
        return new UserDTO(userModel.deleteByID(id));
    }

    
    @Override
    @DeleteMapping(value = "/")
    public void deleteAll(@RequestHeader HttpHeaders httpHeaders,
                          HttpServletRequest request){
        userModel.deleteAll();
    }

    
    @Override
    @PostMapping(value = "/addPage") //TODO: probably delete, duplicated with put
    public void setDataFromForm(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody UserDTO data) {
        UserEntity userEntity = converter.entityFromDTO(data);
        userModel.add(userEntity);
    }

    
    @Override
    @PutMapping(value = "/addPage") //TODO: probably delete, duplicated with put
    public void setDataFromFormDetail(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody UserDTO data, @RequestParam(name = "username") String userName) {
        UserEntity userEntity = userModel.getByUserName(userName);
        userModel.add(userEntity);
    }

    


}
