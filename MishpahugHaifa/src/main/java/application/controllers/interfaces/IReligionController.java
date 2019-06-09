package application.controllers.interfaces;

import application.entities.ReligionEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IReligionController {
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	ReligionEntity get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
	void post(ReligionEntity data, HttpHeaders httpHeaders, HttpServletRequest request);
	void put(Integer id, String name, HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(String name, HttpHeaders httpHeaders, HttpServletRequest request);
}
