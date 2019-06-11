package application.controllers.interfaces;

import application.entities.template.TemplateEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITemplateController {
	TemplateEntity get(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	List<TemplateEntity> getall(HttpHeaders httpHeaders, HttpServletRequest request);
	TemplateEntity post(HttpHeaders httpHeaders, HttpServletRequest request, TemplateEntity templateEntity);
	void remove(HttpHeaders httpHeaders, HttpServletRequest request, String name);
	void removeall(HttpHeaders httpHeaders, HttpServletRequest request);
}
