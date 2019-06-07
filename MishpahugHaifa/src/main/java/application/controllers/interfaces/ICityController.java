package application.controllers.interfaces;

import javax.servlet.http.HttpServletRequest;

import application.exceptions.NotFoundGenderWithIDException;
import org.springframework.http.HttpHeaders;

import com.querydsl.core.types.Predicate;

import application.entities.CityEntity;

import java.util.List;

public interface ICityController {

	void post(CityEntity data, HttpHeaders httpHeaders, HttpServletRequest request) throws NotFoundGenderWithIDException;

	void put(Integer id, String name, HttpHeaders httpHeaders, HttpServletRequest request) throws NotFoundGenderWithIDException;

	void delete(HttpHeaders httpHeaders, HttpServletRequest request);

	void delete(String name, HttpHeaders httpHeaders, HttpServletRequest request) throws NotFoundGenderWithIDException;

	List<String> get(Predicate predicate, HttpHeaders httpHeaders, HttpServletRequest request);

	String get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request) throws NotFoundGenderWithIDException;
}
