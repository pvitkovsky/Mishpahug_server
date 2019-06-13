package application.controllers.interfaces;

import application.entities.properties.KitchenTypeEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IKichenTypeController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, KitchenTypeEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	String get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
