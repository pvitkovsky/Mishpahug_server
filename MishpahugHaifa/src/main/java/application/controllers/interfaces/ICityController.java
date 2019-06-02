package application.controllers.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;

public interface ICityController {

	void post(CityEntity data, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	void put(Integer id, String name, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	void delete(HttpHeaders httpHeaders, HttpServletRequest request);

	void delete(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	Iterable<CityEntity> get(Predicate predicate, HttpHeaders httpHeaders, HttpServletRequest request);

	CityEntity get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;
}
