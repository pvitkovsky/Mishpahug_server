package application.controllers.interfaces;

import application.entities.template.TemplateEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITemplateController {

	TemplateEntity get(String name, HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	List<TemplateEntity> getall(HttpHeaders httpHeaders, HttpServletRequest request) throws ExceptionMishpaha;

	TemplateEntity post(TemplateEntity templateEntity, HttpHeaders httpHeaders, HttpServletRequest request);

	void remove(String name, HttpHeaders httpHeaders, HttpServletRequest request);

	void removeall(HttpHeaders httpHeaders, HttpServletRequest request);
}
