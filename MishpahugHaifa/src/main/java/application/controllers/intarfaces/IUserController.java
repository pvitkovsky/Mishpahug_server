package application.controllers.intarfaces;

import java.util.HashMap;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

import application.dto.UserDTO;
import application.dto.UserDTODetail;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;

public interface IUserController {

	UserEntity get(Integer id) throws ExceptionMishpaha;

	void add(UserDTO userDTO) throws ExceptionMishpaha;

	UserEntity update(HashMap<String, String> data, Integer id) throws ExceptionMishpaha;

	UserEntity delete(Integer id) throws ExceptionMishpaha;

	void delete() throws ExceptionMishpaha;

	void setDataFromForm(UserDTO data) throws ExceptionMishpaha;

	void setDataFromFormDetail(UserDTODetail data, String userName) throws ExceptionMishpaha;

	Iterable<UserEntity> findAllByWebQuerydsl(Predicate predicate);

}