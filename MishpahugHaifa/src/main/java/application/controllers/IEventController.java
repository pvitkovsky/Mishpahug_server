package application.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.dto.UserDTO;

/*
 * Convention for the AOP to work:
 * HttpHeaders is the first argument, HttpServletRequest is the second
 */
public interface IEventController {
	
	//GET
	Page<EventDTO> findAllByWebQuerydsl(HttpHeaders httpHeaders, HttpServletRequest request, Predicate predicate, Pageable pageable); 
	
	EventDTO findById(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	
    List<UserDTO> findGuestByEventId(HttpHeaders httpHeaders, HttpServletRequest request, Integer id); 
    
	//UPDATE
    EventDTO setDataFromForm(HttpHeaders httpHeaders, HttpServletRequest request, EventDTO data);
	
	EventDTO updateDataFromForm(HttpHeaders httpHeaders, HttpServletRequest request, HashMap<String, String> data, Integer id);
	
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	
}
