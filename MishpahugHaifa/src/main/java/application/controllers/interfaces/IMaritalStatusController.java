package application.controllers.interfaces;

import application.entities.MaritalStatusEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IMaritalStatusController {
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	MaritalStatusEntity get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
	void post(MaritalStatusEntity data, HttpHeaders httpHeaders, HttpServletRequest request);
	void put(Integer id, String name, HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(String name, HttpHeaders httpHeaders, HttpServletRequest request);
}
