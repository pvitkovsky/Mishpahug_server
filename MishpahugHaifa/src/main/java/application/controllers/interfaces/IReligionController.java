package application.controllers.interfaces;

import application.entities.properties.ReligionEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IReligionController {
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	ReligionEntity get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	void post(HttpHeaders httpHeaders, HttpServletRequest request, ReligionEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
}
