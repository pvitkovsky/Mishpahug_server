package application.controllers.interfaces;

import application.dto.EventDTO;
import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
import com.querydsl.core.types.Predicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/*
 * Convention for the AOP to work:
 * HttpHeaders is the first argument, HttpServletRequest is the second
 */	
public interface IUserController {
	UserDTO get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	UserDTO getByToken(HttpHeaders httpHeaders, HttpServletRequest request);
	List<EventDTO> getEventsByToken(HttpHeaders httpHeaders, HttpServletRequest request);
	List<EventDTO> getEventsById(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);

    List<EventDTO> getEventsByOwnerId(@RequestHeader HttpHeaders httpHeaders,
                                      HttpServletRequest request, @PathVariable(value = "id") Integer id);

    LoginResponse login(HttpHeaders httpHeaders, HttpServletRequest request, LoginDTO loginDTO) throws FailedLoginException;
	void logout(String token);
	void add(HttpHeaders httpHeaders, HttpServletRequest request, UserDTO userDTO);
	UserDTO update(HttpHeaders httpHeaders, HttpServletRequest request, HashMap<String, String> data, Integer id);
	UserDTO delete(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	void deleteAll(HttpHeaders httpHeaders, HttpServletRequest request);
	void setDataFromForm(HttpHeaders httpHeaders, HttpServletRequest request, UserDTO data);
	void setDataFromFormDetail(HttpHeaders httpHeaders, HttpServletRequest request, UserDTO data, String userName);
	List<UserDTO> findAllByWebQuerydsl(HttpHeaders httpHeaders, HttpServletRequest request, Predicate predicate);
}
