package application.controllers.interfaces;

import application.dto.EventDTO;
import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
import application.entities.EventEntity;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface IUserController {

	UserDTO get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	UserDTO getByToken(HttpServletRequest request, HttpHeaders httpHeaders) throws ExceptionMishpaha;

	// TODO спрятать строки кода в одну из моделей?
	List<EventDTO> getEventsByToken(HttpServletRequest request, @RequestHeader HttpHeaders httpHeaders)
			throws ExceptionMishpaha;

	// TODO спрятать строки кода в одну из моделей?
	List<EventDTO> getEventsById(Integer id, HttpHeaders httpHeaders, HttpServletRequest request)
			throws ExceptionMishpaha;

	LoginResponse login(LoginDTO loginDTO, HttpHeaders httpHeaders, HttpServletRequest request);

	void logout(String token);

	void add(UserDTO userDTO, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	UserDTO update(HashMap<String, String> data, Integer id, HttpHeaders httpHeaders, HttpServletRequest request)
			throws ExceptionMishpaha;

	UserDTO delete(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	void deleteAll(HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	// TODO: not-restful name; better is viewPage1
	void setDataFromForm(UserDTO data, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	// TODO: not-restful name; better is viewPage2
	void setDataFromFormDetail(UserDTO data, String userName, HttpHeaders httpHeaders, HttpServletRequest request)
			throws ExceptionMishpaha;

	List<UserDTO> findAllByWebQuerydsl(Predicate predicate, HttpHeaders httpHeaders, HttpServletRequest request);
}
