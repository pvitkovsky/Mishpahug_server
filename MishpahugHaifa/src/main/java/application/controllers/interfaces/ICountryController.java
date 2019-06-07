package application.controllers.interfaces;

import application.entities.CountryEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICountryController {
	void post(CountryEntity data, HttpHeaders httpHeaders, HttpServletRequest request);
	void put(Integer id, String name, HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(String name, HttpHeaders httpHeaders, HttpServletRequest request);
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	String get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
}
