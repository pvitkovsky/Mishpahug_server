package application.controllers.interfaces;

import application.dto.PropertyDTO;
import application.entities.properties.CityEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICityController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, CityEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	List<PropertyDTO> get(HttpHeaders httpHeaders, HttpServletRequest request, Predicate predicate);
	PropertyDTO get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
