package application.controllers.interfaces;

import application.dto.EventDTO;
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

public interface IEventController {

	List<EventDTO> findAllByWebQuerydsl(Predicate predicate, HttpHeaders httpHeaders, HttpServletRequest request);

	EventEntity findById(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	List<UserEntity> findGuestByEventId(Integer id, HttpHeaders httpHeaders, HttpServletRequest request)
			throws ExceptionMishpaha;

	List<EventDTO> getByOwner(String ownerusername, HttpHeaders httpHeaders, HttpServletRequest request);

	EventEntity setDataFromForm(EventDTO data, HttpHeaders httpHeaders, HttpServletRequest request);

	EventEntity updateDataFromForm(HashMap<String, String> data, Integer id, HttpHeaders httpHeaders,
			HttpServletRequest request) throws ExceptionMishpaha;

	EventEntity delete(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	void delete(HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;
}
