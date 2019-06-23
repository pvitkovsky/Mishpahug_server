package application.controllers.interfaces;

import application.dto.PropertyDTO;
import application.entities.properties.KitchenTypeEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IKichenTypeController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, KitchenTypeEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
					@PathVariable(name = "id") Integer id);
	List<PropertyDTO> get(HttpHeaders httpHeaders, HttpServletRequest request);
	PropertyDTO get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
