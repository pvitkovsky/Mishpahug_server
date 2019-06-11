package application.controllers.interfaces;

import application.entities.CountryEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICountryController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, CountryEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request);
	String get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
