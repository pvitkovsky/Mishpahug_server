package application.controllers;

import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;

/*
 * Convention for the AOP to work:
 * HttpHeaders is the first argument, HttpServletRequest is the second
 */	
public interface IUserController {
	
	//GET
	List<EventDTO> getEventsById(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);	
	
    List<EventDTO> getEventsByOwnerId(HttpHeaders httpHeaders,
                                      HttpServletRequest request, Integer id); 

    List<UserDTO> findAllByWebQuerydsl(HttpHeaders httpHeaders, HttpServletRequest request, Predicate predicate);

	UserDTO get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	
	UserDTO getByToken(HttpHeaders httpHeaders, HttpServletRequest request);

    LoginResponse login(HttpHeaders httpHeaders, HttpServletRequest request, LoginDTO loginDTO) throws FailedLoginException;
   
    void logout(String token);
    
    //UPDATE
	void add(HttpHeaders httpHeaders, HttpServletRequest request, UserDTO userDTO);
	
	UserDTO update(HttpHeaders httpHeaders, HttpServletRequest request, HashMap<String, String> data, Integer id);
	
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
