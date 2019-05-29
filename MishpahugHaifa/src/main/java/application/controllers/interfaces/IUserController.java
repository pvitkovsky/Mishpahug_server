package application.controllers.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.querydsl.core.types.Predicate;

import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;

public interface IUserController {

	UserDTO get(Integer id) throws ExceptionMishpaha;
	
	UserDTO getByToken(HttpServletRequest request) throws ExceptionMishpaha;


    Iterable<UserDTO> findAllByWebQuerydsl(Predicate predicate);
    
    void add(UserDTO userDTO) throws ExceptionMishpaha;

    UserDTO update(UserDTO userDTO, Integer id) throws ExceptionMishpaha;

    UserDTO delete(Integer id) throws ExceptionMishpaha;
	
    void deleteAll() throws ExceptionMishpaha; //TODO: really exposing this to the world? 

    void setDataFromForm(UserDTO data) throws ExceptionMishpaha;

    void setDataFromFormDetail(UserDTO data, String userName) throws ExceptionMishpaha;

    List<EventEntity> getEventsByToken(HttpServletRequest request) throws ExceptionMishpaha;

    List<UserDTO> getall() throws ExceptionMishpaha;

    LoginResponse login(@RequestBody LoginDTO loginDTO, @RequestHeader HttpHeaders httpHeaders,
                        HttpServletRequest request);

    void logout(@RequestHeader(name = "Authorization", required = false) String token);

}