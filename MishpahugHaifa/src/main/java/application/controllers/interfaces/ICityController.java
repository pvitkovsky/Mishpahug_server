package application.controllers.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;

import java.util.List;

public interface ICityController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, CityEntity data);
	void put(HttpHeaders httpHeaders, HttpServletRequest request, Integer id, String name);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	List<String> get(HttpHeaders httpHeaders, HttpServletRequest request, Predicate predicate);
	String get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
