package application.controllers.interfaces;

import application.entities.KitchenTypeEntity;
import application.exceptions.EntityExistsDException;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IKichenTypeController {
	void post(KitchenTypeEntity data, HttpHeaders httpHeaders, HttpServletRequest request);
	void put(Integer id, String name, HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(String name, HttpHeaders httpHeaders, HttpServletRequest request);
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	String get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
}
