package application.controllers;

import java.util.HashMap;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;

public interface IEventController {

	Iterable<EventEntity> findAllByWebQuerydsl(Predicate predicate);

	EventEntity findAll(Integer id) throws ExceptionMishpaha;

	EventEntity setDataFromForm(EventDTO data);

	EventEntity updateDataFromForm(HashMap<String, String> data, Integer id) throws ExceptionMishpaha;

	EventEntity delete(Integer id) throws ExceptionMishpaha;

	void delete() throws ExceptionMishpaha;

}