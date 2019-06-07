package application.controllers.interfaces;

import application.entities.template.TemplateEntity;
import application.exceptions.NotFoundGenderWithIDException;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITemplateController {

	TemplateEntity get(String name, HttpHeaders httpHeaders, HttpServletRequest request) throws NotFoundGenderWithIDException;

	List<TemplateEntity> getall(HttpHeaders httpHeaders, HttpServletRequest request) throws NotFoundGenderWithIDException;

	TemplateEntity post(TemplateEntity templateEntity, HttpHeaders httpHeaders, HttpServletRequest request);

	void remove(String name, HttpHeaders httpHeaders, HttpServletRequest request);

	void removeall(HttpHeaders httpHeaders, HttpServletRequest request);
}
