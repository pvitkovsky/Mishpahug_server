package application.controllers.interfaces;

import application.dto.EventDTO;
import application.dto.UserDTO;
import com.querydsl.core.types.Predicate;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface IEventController {
	List<EventDTO> findAllByWebQuerydsl(Predicate predicate, HttpHeaders httpHeaders, HttpServletRequest request);
	EventDTO findById(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
	List<UserDTO> findGuestByEventId(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
	List<EventDTO> getByOwner(String ownerusername, HttpHeaders httpHeaders, HttpServletRequest request);
	EventDTO setDataFromForm(EventDTO data, HttpHeaders httpHeaders, HttpServletRequest request);
	EventDTO updateDataFromForm(HashMap<String, String> data, Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
}
