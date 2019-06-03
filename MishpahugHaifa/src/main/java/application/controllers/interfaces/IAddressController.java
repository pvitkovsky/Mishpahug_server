package application.controllers.interfaces;

import application.entities.AddressEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAddressController {

	void post(AddressEntity data, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	void delete(HttpHeaders httpHeaders, HttpServletRequest request);

	void delete(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	String get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;
}
