package application.controllers.intarfaces;

import java.util.HashMap;

import application.dto.LoginDTO;
import application.dto.LoginResponse;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import application.dto.UserDTO;
import application.dto.UserDTODetail;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;

import javax.servlet.http.HttpServletRequest;

public interface IUserController {

	UserEntity get(Integer id) throws ExceptionMishpaha;

	@PostMapping(value = "/login")
	LoginResponse login(@RequestBody LoginDTO loginDTO, @RequestHeader HttpHeaders httpHeaders,
						HttpServletRequest request);

	@PostMapping(value = "/logout")
	void logout(@RequestHeader(name = "Authorization", required = false) String token);

	void add(UserDTO userDTO) throws ExceptionMishpaha;

	UserEntity update(HashMap<String, String> data, Integer id) throws ExceptionMishpaha;

	UserEntity delete(Integer id) throws ExceptionMishpaha;

	void delete() throws ExceptionMishpaha;

	void setDataFromForm(UserDTO data) throws ExceptionMishpaha;

	void setDataFromFormDetail(UserDTODetail data, String userName) throws ExceptionMishpaha;

	Iterable<UserEntity> findAllByWebQuerydsl(Predicate predicate);

}