package application.controllers.interfaces;

import application.dto.PropertyDTO;
import application.entities.properties.MaritalStatusEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IMaritalStatusController {
	List<PropertyDTO> get(HttpHeaders httpHeaders, HttpServletRequest request);
	PropertyDTO get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	void post(HttpHeaders httpHeaders, HttpServletRequest request, MaritalStatusEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
