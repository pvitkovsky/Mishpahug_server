package application.controllers.interfaces;

import application.entities.AddressEntity;
import application.exceptions.EntityExistsDException;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public interface IAddressController {
	void post(AddressEntity data, HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
	String get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
}
