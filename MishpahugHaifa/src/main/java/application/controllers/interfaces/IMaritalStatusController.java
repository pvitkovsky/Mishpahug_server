package application.controllers.interfaces;

import application.entities.MaritalStatusEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IMaritalStatusController {
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	MaritalStatusEntity get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	void post(HttpHeaders httpHeaders, HttpServletRequest request, MaritalStatusEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
}
