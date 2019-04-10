package application.controllers.intarfaces;

import java.util.HashMap;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;

public interface IEventController {

	Iterable<EventEntity> findAllByWebQuerydsl(Predicate predicate, @RequestHeader HttpHeaders httpHeaders);

	EventEntity findAll(Integer id, @RequestHeader HttpHeaders httpHeaders) throws ExceptionMishpaha;

	EventEntity setDataFromForm(EventDTO data);

	EventEntity updateDataFromForm(HashMap<String, String> data, Integer id) throws ExceptionMishpaha;

	EventEntity delete(Integer id) throws ExceptionMishpaha;

	void delete() throws ExceptionMishpaha;

}