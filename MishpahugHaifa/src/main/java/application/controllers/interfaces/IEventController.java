package application.controllers.interfaces;

import application.dto.EventDTO;
import application.dto.UserDTO;
import com.querydsl.core.types.Predicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/*
 * Convention for the AOP to work:
 * HttpHeaders is the first argument, HttpServletRequest is the second
 */
public interface IEventController {
	List<EventDTO> findAllByWebQuerydsl(HttpHeaders httpHeaders, HttpServletRequest request, Predicate predicate);
	EventDTO findById(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
    List<UserDTO> findGuestByEventId(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	EventDTO setDataFromForm(HttpHeaders httpHeaders, HttpServletRequest request, EventDTO data);
	EventDTO updateDataFromForm(HttpHeaders httpHeaders, HttpServletRequest request, HashMap<String, String> data, Integer id);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
}
