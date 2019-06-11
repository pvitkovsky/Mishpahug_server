package application.controllers.interfaces;

import application.entities.AddressEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public interface IAddressController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, AddressEntity data);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	String get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
