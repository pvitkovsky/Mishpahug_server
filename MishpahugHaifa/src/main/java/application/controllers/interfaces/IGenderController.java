package application.controllers.interfaces;

import application.entities.properties.GenderEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IGenderController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, GenderEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	GenderEntity get(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	String get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
