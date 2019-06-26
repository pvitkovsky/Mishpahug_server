package application.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

public interface IChoiceController {
	public Map<String, String[]> getAllowedChoices(HttpHeaders httpHeaders, HttpServletRequest request);
}
